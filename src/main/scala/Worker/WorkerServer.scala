package Worker

import scala.concurrent.{ExecutionContext, Future}

import Network.{ServerBase, ServerInterface}
import Communicate.network.{MasterWorkerServiceGrpc, SortDataMsg, ResponseMsg}

object WorkerServer extends ServerInterface{
  val server: ServerBase = new ServerBase(
    MasterWorkerServiceGrpc.bindService(new MasterWorkerServiceImpl, ExecutionContext.global),
    "WorkerServer",
    5555
  )
}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService {
  override def sortData(sortData: SortDataMsg): Future[ResponseMsg] = {
    // sort에 대해서 handling해야하는 부분 추가
    
    Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS ))
  }
}

