package master

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{ExecutionContext, Future}

import worker.WorkerStorage._


import network.{ServerBase, ServerInterface}
import communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg, DivideMsg, SortDataMsg, SampleDataMsg, SampleArrayMsg}


// import Master.WorkerClient

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
      Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS))
    }
    else
    {
      logger.error("Worker register failed")
      Future.successful(new ResponseMsg(ResponseMsg.ResponseType.ERROR))
    }
  }

  override def divideData(divideData: DivideMsg): Future[ResponseMsg] = {
    Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS))
  }

  override def sortingData(sortData: SortDataMsg): Future[ResponseMsg] = {
    Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS))
  }
  
  //override def sampleDataResponse(sampleData: SampleDataMsg): Future[SampleArrayMsg] = {
    //Future.successful(new SampleArrayMsg(여기다가 뭘 넣을것인가))
  //}
}
