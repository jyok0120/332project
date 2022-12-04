package Master

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{ExecutionContext, Future}

import Worker.WorkerStorage._

import Network.{ServerBase, ServerInterface}
import Communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg, ReportMsg}

import Master.WorkerClient

object MasterServer extends ServerInterface {
  val server : ServerBase = new ServerBase (
    MasterWorkerServiceGrpc.bindService(new MasterWorkerServiceImpl, ExecutionContext.global),
    "MasterServer",
    1234
  )
}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService with Logging{
  override def registerWorker(request: RegisterMsg): Future[ResponseMsg] = {

    logger.error("Current worker's address and port " + request.address + " " + request.port)
    // maybe manage channel here
    if(!checkWorkerExist(request.address, request.port))
    {
      val workerId = addWorker(request.address, request.port)
      val workerClient: WorkerClient = new WorkerClient(request.address,request.port) // host and port parameter
      Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS))
    }
    else
    {
      logger.error("Worker register failed")
      Future.successful(new ResponseMsg(ResponseMsg.ResponseType.ERROR))
    }
    
  }
  override def reportMaster(request: ReportMsg): Future[ResponseMsg] = {
    Future.successful( new ResponseMsg(ResponseMsg.ResponseType.SUCCESS) )
  }
}
