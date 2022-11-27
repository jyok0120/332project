package Master

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{ExecutionContext, Future}

import Network.{ServerBase, ServerInterface}
import Communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg, ReportMsg}

object MasterServer extends ServerInterface {
  val server : ServerBase = new ServerBase (
    MasterWorkerServiceGrpc.bindService(new MasterWorkerServiceImpl, ExecutionContext.global),
    "MasterServer",
    2201
  )
}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService with Logging{
  override def registerWorker(request: RegisterMsg): Future[ResponseMsg] = {
    // maybe manage channel here
    Future.successful( new ResponseMsg(ResponseMsg.ResponseType.SUCCESS) )
  }
  override def reportMaster(request: ReportMsg): Future[ResponseMsg] = {
    Future.successful( new ResponseMsg(ResponseMsg.ResponseType.SUCCESS) )
  }
}
