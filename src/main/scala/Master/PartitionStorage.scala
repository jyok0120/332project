package master

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import partition.Partition

object PartitionStorage {
    private val partitionStorage = mutable.SortedMap[Int, ListBuffer[String]]()

    def getWorkersIndex: Iterable[Int] = {
        val resultIndex = partitionStorage.keys
        resultIndex
    }

    def getPartitionList(workerIndex : Int):ListBuffer[String] = {
        val resultList = partitionStorage(workerIndex)
        resultList
    } 

    //* 이름 바꾸고 싶음 알아서 바꾸셈~
    def getRecordLineNumber: Int = {
        val lineNumber = getWorkersIndex.map(key => 
                    PartitionStorage(key).map(partition => partition.partitionRecordNumber).sum).sum

        lineNumber
    }
}