package Worker

import scala.collection.mutable


object WorkerStorage {
	private val workerStorage : mutable.Map[Int, (String, Int)] = mutable.Map[Int, (String, Int)]()

    def addWorker(Host : String, Port : Int) = {
        val index = workerStorage.keysIterator.max + 1
        workerStorage += (index -> (Host, Port))
    }

    def getWorkerNumber: Int = workerStorage.size

    def checkWorkerExist(Host : String, Port : Int): Boolean = {
        workerStorage.values.exists(_._1 == Host) && workerStorage.values.exists(_._2 == Port)
    }
}