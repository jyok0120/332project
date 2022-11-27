package Worker

import org.apache.logging.log4j.scala.Logging

import Communicate.network.{RegisterMsg, ResponseMsg}
import Worker.{WorkerServer, WorkerClient}

object Main extends Logging{
  def main(args: Array[String]): Unit = {
    logger.info("Worker start")
    // Start Networking Service
    WorkerServer.startserver

    // Channel manage

    
    // Register worker to master
    // val workerClient: WorkerClient = new WorkerClient("2.2.2.101",22) // host and port parameter
    
    // logger.info(s"Trying to register worker to channel ${workerClient}")
    
    // println("WorkerClient has made")

    // val registerResponse: ResponseMsg = workerClient.registerWorker(new RegisterMsg("2.2.2.103",22))

    // println("RegisterWorker has made")
    
    // if( registerResponse.response == ResponseMsg.ResponseType.ERROR)
    // {
    //   logger.error("Failed to register Worker")
    //   return
    // }
    logger.info("Worker Master register successed")

    // Worker's state machine


    // End Networking Service
    WorkerServer.stopserver
    logger.info("Worker finish")
  }
}