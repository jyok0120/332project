package utils

import org.apache.logging.log4j.scala.Logging

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting
import scala.collection._
import scala.concurrent.{Future, ExecutionContext}
import scala.math._
 
// Goal: offer a generic external-memory sorting program in Java.

object SortingUtils extends Logging {
    private object KeyOrdering extends Ordering[Array[Byte]] {
        override def compare(x: Array[Byte], y: Array[Byte]): Int = {
        // In order to sort by ascending order,
        // it returns positive when x > y
        assert( x.size == y.size )
        _compare( x.toList, y.toList )
        }

        def _compare(x: List[Byte], y: List[Byte] ): Int = {
            if( x.isEmpty ) 0
            else {
                if( x.head > y.head ) 1
                else if (x.head < y.head) -1
                else _compare( x.drop(1), y.drop(1) )
            }
        }

    }

    def sortLines( lines: Array[Array[Byte]] ): Array[Array[Byte]] = {
        /* The algorithm is a in-place sorting algorithm
        * Thus no additional memory that exceeds
        * the partition size will be required
        * */
        logger.debug(s"quicksort ${lines.size} lines")
        Sorting.quickSort(lines)( KeyOrdering )
        logger.debug(s"sort finished")
        lines
    }

    // def sort(input: String, tmpDir: String, output: String, linesPerChunk: Int)
    //     (implicit ec: ExecutionContext): Future[String] = {

    //     // split file into n chunks
    //     val chunks = splitStep(input, linesPerChunk, tmpDir)

    //     // sort each chunk locally, in parallel
    //     val sortAsync = sortStep(chunks)

    //     // merge sorted chunks using k-way merge algorithm
    //     val merge = sortAsync map { sorted =>
    //         val n = max(1, linesPerChunk / sorted.size) // n should be between 1 to linesPerChunk / sorted.size
    //         mergeStep(sorted, output, n)
    //     }

    //     // clean up
    //     merge onSuccess { case _ => IO.delete(chunks) }

    //     merge
    // }

    // def splitStep(in: String, linesPerChunk: Int, tmp: String): Seq[String] = {
    //     val (handle, lines) = IO.readLines(in)
    //     val chunked = lines.grouped(linesPerChunk).zipWithIndex

    //     val chunks = chunked map { case (chunk, id) =>
    //         val out = s"$tmp/chunk-$id"
    //         IO.writeSeq(chunk, out, true)
    //         out
    //     } toList

    //     handle.close()

    //     chunks 
    // }

    // // sort in memory
    // // must limit the number of instance being processed concurrently to prevent OutOfMemoryException
    // def sortStep(chunks: Seq[String])(implicit ec: ExecutionContext): Future[Seq[String]] = {
    //     val async  = chunks map { path => Future(sortChunk(path)) }
    //     val joined = Future.sequence(async)
    //     joined
    // }

    // def sortChunk(path: String): String = {
    //     val (handle, lines) = IO.readLines(path)
    //     val it = lines.toArray.map(_.toInt)
    //     handle.close() // clean up
    //     IO.writeSeq(it.sorted.map(_.toString), path, true) // 1 pass to write
    // }
}