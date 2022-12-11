package master

import org.apache.logging.log4j.scala.Logging

object MasterArgument extends Logging{
    var NumWorker: Int = 4

    def setWorkerNum (argument : Array[String]): Unit = {
        try {
            NumWorker = argument(0).toInt
        }
        catch {
            case v : Throwable => {
                logger.error("Wrong argument")
                throw v
            }
        }
    }
}
