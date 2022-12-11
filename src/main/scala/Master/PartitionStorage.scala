package master

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object PartitionStorage {
    private val partitionStorage = mutable.SortedMap[Int, ListBuffer[String]]()

    def getWorkersIndex: Iterable[Int] = {
        partitionStorage.keys
    }

    def getPartitionList(workerIndex : Int):mutable.ListBuffer[String] = {
        partitionStorage(workerIndex)
    } 
}