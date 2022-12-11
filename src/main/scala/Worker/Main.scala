package worker

import org.apache.logging.log4j.scala.Logging
import communicate.network.{RegisterMsg, ResponseMsg}

object Main extends Logging{
  def main(args: Array[String]): Unit = {

    
    logger.info("Worker start")
    // Start Networking Service
    WorkerServer.startserver

    // Channel manage
    
    // Register worker to master
    val masterStub: MasterStub = new MasterStub("2.2.2.101",1234) // host and port parameter
    
    logger.error(s"Trying to register worker to channel ${masterStub}")
    
    //println("WorkerClient has made")

    val registerResponse: ResponseMsg = masterStub.registerWorker(RegisterMsg(address = "2.2.2.107", port = 7777))

    //println("RegisterWorker has made")
    
    if( registerResponse.response == ResponseMsg.ResponseType.ERROR)
    {
      logger.error("Failed to register Worker")
      return
    }
    logger.error("Worker Master register successed")

    // Worker's state machine

    // End Networking Service
    WorkerServer.stopserver
    logger.error("Worker finish")
  }
}
