package worker

import scala.concurrent.{ExecutionContext, Future}


import network.{ServerBase, ServerInterface}
import communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, DivideMsg, SortDataMsg, ResponseMsg}


object WorkerServer extends ServerInterface{
  val server: ServerBase = new ServerBase(
    MasterWorkerServiceGrpc.bindService(new MasterWorkerServiceImpl, ExecutionContext.global),
    "WorkerServer",
    7777
  )
}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService {
  override def registerWorker(request: RegisterMsg): Future[ResponseMsg] = {
    Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS ))
  }

  override def divideData(divideData: DivideMsg): Future[ResponseMsg] = {
    Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS ))
  }

  override def sortingData(sortData: SortDataMsg): Future[ResponseMsg] = {
    // sort에 대해서 handling해야하는 부분 추가
    
    Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS ))
  }
}

