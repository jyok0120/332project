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

    def getLinesFromFile(filePath: String, lineOffset: Int, numLines: Int, lineLengthBytes: Int = 100): Array[Array[Byte]] = {
        val inputStream = new BufferedInputStream(new FileInputStream(filePath))

        try {
            inputStream skip (lineOffset * lineLengthBytes)
            val outputArray = Array.fill[Array[Byte]](numLines)(Array.fill[Byte](lineLengthBytes)(Byte.MinValue))

            for (lineIdx <- outputArray.indices)
                inputStream.read(outputArray(lineIdx), 0, lineLengthBytes)
            outputArray
        } finally {
            inputStream.close
        }
    }
}