package utils
import org.apache.logging.log4j.scala.Logging
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import java.io._

object PartitioningUtils extends Logging {
    def compareKey(x: Array[Byte], y:Array[Byte]): Int = {
        assert(x.size == y.size)

        val xList = x.toList
        val yList = y.toList

        def _compareKey(xList: List[Byte], yList: List[Byte]): Int = {
            if (xList.isEmpty) 0
            else {
                if (xList.head > yList.head) 1
                else if (xList.head < yList.head) -1
                else _compareKey(xList.drop(1), yList.drop(1))
            }
        }
        _compareKey(xList, yList)
    }

    def partition(filePath: String, partitionKeyTable: Array[(Array[Byte], (String, Int))], 
                                    partitionArray: Array[ArrayBuffer[Array[Byte]]], numLines:Int) = {
        val inputStream = new BufferedInputStream(new FileInputStream(filePath))

        try {
            for(lineIndex <- 0 until numLines) {
                val line = Array.fill[Byte](100)(0)
                inputStream.read(line, 0, 100)

                val key = line.slice(0, 10)

                val pivot = partitionKeyTable.zipWithIndex.filter( kv => {
                            assert(kv._1._1.size == key.size && key.size == 10)

                            compareKey(kv._1._1, key) >= 0}
                        ).head._2

                partitionArray(pivot).append(line)
            }
        } finally {
            inputStream.close
        }
    }
}