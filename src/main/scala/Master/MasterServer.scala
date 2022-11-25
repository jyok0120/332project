package Master

import scala.concurrent.{ExecutionContext, Future}

import Network.{ServerBase, ServerInterface}
import Communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg}

object MasterServer extends ServerInterface {
  val server : ServerBase = new ServerBase (
    MasterWorkerServiceGrpc.bindService(new MasterWorkerServiceImpl, ExecutionContext.global),
    "MasterServer",
    2201
  )
}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService {
  override def RegisterWorker(request: RegisterMsg): Future[ResponseMsg] = {
    // maybe manage channel here
    Future.successful( new ResponseMsg(ResponseMsg.ResponseType.SUCCESS ) )
  }
}
