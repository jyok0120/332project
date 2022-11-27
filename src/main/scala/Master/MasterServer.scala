package Master

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{ExecutionContext, Future}

import Worker.WorkerStorage._

import Network.{ServerBase, ServerInterface}
import Communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg, ReportMsg}

object MasterServer extends ServerInterface {
  val server : ServerBase = new ServerBase (
    MasterWorkerServiceGrpc.bindService(new MasterWorkerServiceImpl, ExecutionContext.global),
    "MasterServer",
    1234
  )
}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService with Logging{
  override def registerWorker(request: RegisterMsg): Future[ResponseMsg] = {
    // maybe manage channel here
    if(checkWorkerExist(request.address, request.port))
    {
      val workerId = addWorker(request.address, request.port)
      Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS))
    }
    else
    {
      Future.successful(new ResponseMsg(ResponseMsg.ResponseType.ERROR))
    }
    
  }
  override def reportMaster(request: ReportMsg): Future[ResponseMsg] = {
    Future.successful( new ResponseMsg(ResponseMsg.ResponseType.SUCCESS) )
  }
}
