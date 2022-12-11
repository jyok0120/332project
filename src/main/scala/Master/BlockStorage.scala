package master

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import java.util.concurrent.locks.ReentrantLock
import block.Block

object BlockStorage {
    private val blockStorage = mutable.SortedMap[Int, ListBuffer[Block]]()
    val storageLock = new ReentrantLock()

    def showStatus(): String = {
        val status: String = getWorkersIndex
                                .map(workerIndex => s"[Worker ${workerIndex}]\n ${blockStorage(workerIndex)
                                .map(block => block.blockName + " - " + block.blockRecordNumber.toString)
                                .mkString("\n")} \n")
                                .foldLeft("")(_ + _)
        s"[BlockStorage Status]\n\n${status}"
    }

    def createNewBlock(blockName: String): Block = {
        val newBlock = new Block(blockName)
        newBlock
    }

    def addBlock(workerIndex: Int, block: Block): Unit = {
        storageLock.lock()
        
        // if new workerIndex
        if (!blockStorage.contains(workerIndex)) {
            blockStorage.put(workerIndex, new ListBuffer[Block]())
        }

        blockStorage(workerIndex).append(block)

        storageLock.unlock()
    }

    def deleteBlockByName(workerIndex: Int, blockName: String): Unit = {
        storageLock.lock()

        blockStorage(workerIndex).remove(blockStorage(workerIndex).indexWhere(_.blockName equals blockName))

        storageLock.unlock()
    }

    def getWorkersIndex: Iterable[Int] = {
        val resultIndex = blockStorage.keys
        resultIndex
    }

    def getBlockList(workerIndex : Int):ListBuffer[Block] = {
        val resultList = blockStorage(workerIndex)
        resultList
    } 

    def getRecordLineNumber: Int = {
        val lineNumber = getWorkersIndex.map(key => blockStorage(key)
                                            .map(block => block.blockRecordNumber).sum).sum

        lineNumber
    }
}