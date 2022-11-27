package utils
import org.apache.logging.log4j.scala.Logging
import java.io._

object FileIoUtils extends Logging {
    def getNumLinesInFile(filePath: String): Int = {
        val inputReader = new BufferedReader( new FileReader( filePath ) )

        try {
            val size = inputReader.lines().count().toInt
            inputReader.close
            size
        } finally {
            inputReader.close
        }
    }

    def getLinesFromFile(filePath: String, lineOffset: Int, numLines: Int, lineLengthBytes: Int = 100) = {
        logger.info(s"READ: ${numLines} lines, ${lineLengthBytes} bytes/line")

        val inputStream = new BufferedInputStream(new FileInputStream(filePath))

        try {
            inputStream skip (lineOffset * lineLengthBytes)
            val outputArr = Array.fill[Array[Byte]](numLines)(Array.fill[Byte](lineLengthBytes)(Byte.MinValue))

            for (lineIdx <- outputArr.indices)
                inputStream.read(outputArr(lineIdx), 0, lineLengthBytes)
            outputArr
        } finally {
            inputStream.close
        }
    }
}