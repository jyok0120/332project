package worker

import scala.collection.mutable

import org.apache.logging.log4j.scala.Logging

object WorkerStorage extends Logging{
	private val workerStorage : mutable.Map[Int, (String, Int)] = mutable.Map[Int, (String, Int)]()

    def addWorker(Host : String, Port : Int) = 
        if(workerStorage.isEmpty) {
            val index = 1
            workerStorage += (index -> (Host, Port))
        }
        else
        {
            val index = workerStorage.keysIterator.max + 1
        workerStorage += (index -> (Host, Port))
        }

    def getWorkerNumber: Int = workerStorage.size

    def checkWorkerExist(Host : String, Port : Int): Boolean = {
        workerStorage.values.exists(_._1 == Host) && workerStorage.values.exists(_._2 == Port)
    }

    def getWorkerAddress(workerIndex : Int): (String, Int) = workerStorage(workerIndex)
}