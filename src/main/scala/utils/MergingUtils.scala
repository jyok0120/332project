package utils
import org.apache.logging.log4j.scala.Logging
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import java.io._
import utils.PartitioningUtils


object MergingUtils extends Logging {
    /** Merging two files into a sorted file
     *  
     *  @param inputFilePath1 first input file's path
     *  @param inputFilePath2 second input file's path
     *  @param outputFilePath merged, sorted output file's path
     *  @param lineLengthBytes length of each line in bytes. default is 100.
     */
    def mergingFiles(inputFilePath1: String, inputFilePath2: String, outputFilePath: String, lineLengthBytes: Int = 100) = {
        val inputStream1 = new BufferedInputStream(new FileInputStream(inputFilePath1))
        val inputStream2 = new BufferedInputStream(new FileInputStream(inputFilePath2))
        val outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath))

        try {
            val inputLine1 = Array.fill[Byte](lineLengthBytes)(0)
            val inputLine2 = Array.fill[Byte](lineLengthBytes)(0)
            
            // read a line from each files
            val readResult1 = inputStream1.read(inputLine1, 0, lineLengthBytes)
            val readResult2 = inputStream2.read(inputLine2, 0, lineLengthBytes)

            assert(readResult1 != -1 && readResult2 != -1)

            def _merge(inputFile1End: Boolean, inputFile2End: Boolean): Unit = {
                def writeFile1() = {
                    outputStream.write(inputLine1)

                    val readResult = inputStream1.read(inputLine1, 0, lineLengthBytes)
                    readResult match {
                        case -1 => _merge(true, false)
                        case _ => _merge(false, false)
                    }
                }
                def writeFile2() = {
                    outputStream.write(inputLine2)

                    val readResult = inputStream2.read(inputLine2, 0, lineLengthBytes)
                    readResult match {
                        case -1 => _merge(false, true)
                        case _ => _merge(false, false)
                    }
                }

                (inputFile1End, inputFile2End) match {
                    case (false, false) => {
                        val compareKeyVal = PartitioningUtils.compareKey(inputLine1, inputLine2)
                        if (compareKeyVal < 0) {
                            writeFile1()
                        } else {
                            writeFile2()
                        }
                    }
                    case (false, true) => writeFile1()
                    case (true, false) => writeFile2()
                }
            }
            _merge(false, false)
        } finally {
            inputStream1.close()
            inputStream2.close()
            outputStream.close()
        }
    }
}