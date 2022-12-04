package Master

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

import Master.MasterServer
import Worker.WorkerStorage

object Main extends Logging{
  def main(args: Array[String]): Unit = {
    logger.info("Master start")
    // Start Networking Service
    MasterServer.startserver

    // Check Master - Worker register
    logger.info("Waiting for workers")
    
    val workerRegisterSuccess = Future {
      while(WorkerStorage.getWorkerNumber < 1)
      {
        Thread.sleep(3000)
      }
    }
    val workerRegisterSuccessWait = Await.result(workerRegisterSuccess, Duration.Inf)
    
    // Master's state machine


    // End Networking Service
    MasterServer.stopserver
    logger.info("Master finish")
    println("Master finish")
  }
}