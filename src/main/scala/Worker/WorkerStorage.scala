package worker

import scala.collection.mutable

import org.apache.logging.log4j.scala.Logging
import utils.IndexingUtils._

object WorkerStorage extends Logging{
	private val workerStorage : mutable.Map[Int, (String, Int)] = mutable.Map[Int, (String, Int)]()

    def addWorker(Host : String, Port : Int) : Int = {
        val index: Int = createNewWorkerIndex(workerStorage.keySet.toSet)
        val address: (String, Int) = (Host,Port)
        workerStorage += (index -> address)
        index
    }   

    def getWorkerNumber: Int = workerStorage.size

    def checkWorkerExist(Host : String, Port : Int): Boolean = {
        workerStorage.values.exists(_._1 == Host) && workerStorage.values.exists(_._2 == Port)
    }

    def getWorkerAddress(workerIndex : Int): (String, Int) = workerStorage(workerIndex)
}