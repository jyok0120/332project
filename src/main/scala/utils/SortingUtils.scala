package utils

import org.apache.logging.log4j.scala.Logging

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting
import scala.collection._
import scala.concurrent.{Future, ExecutionContext}
import scala.math._
 

object SortingUtils extends Logging {
    private object KeyOrdering extends Ordering[Array[Byte]] {
        override def compare(x: Array[Byte], y: Array[Byte]): Int = {
            logger.info(s"compare key")
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
    }

    def sortLines( lines: Array[Array[Byte]] ): Array[Array[Byte]] = {
        logger.info(s"sort start")
        Sorting.quickSort(lines)( KeyOrdering )
        logger.info(s"sort finish")
        lines
    }

}