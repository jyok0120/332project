package Worker

import scala.collection.mutable

<<<<<<< HEAD

object WorkerStorage {
	private val workerStorage : mutable.Map[Int, (String, Int)] = mutable.Map[Int, (String, Int)]()

    def addWorker(Host : String, Port : Int) = {
        val index = workerStorage.keysIterator.max + 1
        workerStorage += (index -> (Host, Port))
    }
=======
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
>>>>>>> c82d93fe43f98ef7ad13a90dd9b67d3713999e80

    def getWorkerNumber: Int = workerStorage.size

    def checkWorkerExist(Host : String, Port : Int): Boolean = {
        workerStorage.values.exists(_._1 == Host) && workerStorage.values.exists(_._2 == Port)
    }
}