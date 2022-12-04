package utils

import org.apache.logging.log4j.scala.Logging
import scala.collection._
import scala.concurrent.{Future, ExecutionContext}
import scala.math._
 
// Goal: offer a generic external-memory sorting program in Java.

object SortingUtils extends Logging {
    /*
    def splitText(input: Array[Array[Byte]], output: Array[Array[Byte]]): Array[Array[Byte]] = {
        input
    }

    def compare(input: Array[Array[Byte]], output: Array[Array[Byte]])
    */
    def sort(input: String, tmpDir: String, output: String, linesPerChunk: Int)
        (implicit ec: ExecutionContext): Future[String] = {

        // split file into n chunks
        val chunks = splitStep(input, linesPerChunk, tmpDir)

        // sort each chunk locally, in parallel
        val sortAsync = sortStep(chunks)

        // merge sorted chunks using k-way merge algorithm
        val merge = sortAsync map { sorted =>
            val n = max(1, linesPerChunk / sorted.size) // n should be between 1 to linesPerChunk / sorted.size
            mergeStep(sorted, output, n)
        }

        // clean up
        merge onSuccess { case _ => IO.delete(chunks) }

        merge
    }

    def splitStep(in: String, linesPerChunk: Int, tmp: String): Seq[String] = {
        val (handle, lines) = IO.readLines(in)
        val chunked = lines.grouped(linesPerChunk).zipWithIndex

        val chunks = chunked map { case (chunk, id) =>
            val out = s"$tmp/chunk-$id"
            IO.writeSeq(chunk, out, true)
            out
        } toList

        handle.close()

        chunks 
    }

    // sort in memory
    // must limit the number of instance being processed concurrently to prevent OutOfMemoryException
    def sortStep(chunks: Seq[String])(implicit ec: ExecutionContext): Future[Seq[String]] = {
        val async  = chunks map { path => Future(sortChunk(path)) }
        val joined = Future.sequence(async)
        joined
    }

    def sortChunk(path: String): String = {
        val (handle, lines) = IO.readLines(path)
        val it = lines.toArray.map(_.toInt)
        handle.close() // clean up
        IO.writeSeq(it.sorted.map(_.toString), path, true) // 1 pass to write
    }

    // using k-way merging algorithm to merge sorted chunks
    def mergeStep(chunks: Seq[String], out: String, linesPerChunk: Int): String = {
        // initialize variables - priority queue, file readers
        val readers = chunks.zipWithIndex map { case (chunk, id) =>
        val (handle, it) = IO.readLines(chunk)
        // chunk id is used to idenfity which chunk reader to advance after dequeing an element from the queue
        val indexed = it map { e => (e, id) }
        (handle, indexed)
        } toVector
        val totalSize = readers.size
        var closedReaders = mutable.Set[Int]()
        // the priority queue stores (Int, Int), i.e., the actual value as well as the chunk id where it resides in
        // a crucial part of the k-way merge algorithm is to advance the reader of a particular chunk reader
        // after an element has been dequeued from the queue, hence why we store the chunk id along with the actual element
        val sortedQueue = new ScalaQueue[(Int, Int)](Ordering.by { case (v, i) => -v})

        // initialize the priority queue with the first lines of each chunks
        val lines = readers flatMap { case (_, it) =>
            val tmp = it.take(linesPerChunk).map { case (v, i) => (v.toInt, i) }
            tmp toSeq
        }
        lines foreach { e => sortedQueue.enqueue(e) }

        // then, iterate over the rest of the chunks and put values into the priority queue,
        // before finally writing the sorted data to 'out'
        IO.overwrite(out) { writer =>
        while (closedReaders.size < totalSize || sortedQueue.notEmpty) {
            // dequeue an element 'v1' and write it to 'out'
            val (e, chunkId) = sortedQueue.dequeue()
            writer.write(s"$e")
            writer.newLine()

            // then read the next line from chunk 'chunkId' put them in the priority queue
            val next = readers(chunkId)._2
            .map { case (v, i) => (v.toInt, i) }
            .toSeq
            next foreach { e => sortedQueue.enqueue(e) }

            // check if chunk 'chunkId' has been fully processed
            // if it has, close the reader and store the id in 'closedReaders'
            val it     = readers(chunkId)._2
            val handle = readers(chunkId)._1
            if (it.isEmpty) {
            // close reader and put the id in closedReaders set
            val alreadyClosed = closedReaders.contains(chunkId)
            if (!alreadyClosed) {
                handle.close()
                closedReaders += chunkId
            }
            else {
                // do nothing, reader is already closed
            }
            }
        }
        }
        out
    }
}