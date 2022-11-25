package Worker

import scala.concurrent.{ExecutionContext, Future}
import io.grpc.{ManagedChannel, ManagedChannelBuilder, StatusRuntimeException}

import Network.{ServerBase, ServerInterface}
import Network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg}

object WorkerServer extends ServerInterface{
  val server: ServerBase = new ServerBase(
    MasterWorkerServiceGrpc.bindService(new MasterWorkerServiceImpl, ExecutionContext.global),
    "WorkerServer",
    2203
  )
}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService {
  override def ReportMaster(request: ReportMsg): Future[ResponseMsg] = {
    Future.successful(new ResponseMsg(ResponseMsg.ResponseType.SUCCESS ))
  }
}

class WorkerClient( ip : String, port : Int) {
    private val masterChannel = ManagedChannelBuilder.forAddress(ip,port).usePlaintext.ManagedChannelBuilder
    private val masterBlockingStub = MasterWorkerServiceGrpc.blockingStub(masterChannel)

    def RegisterWorker(request: RegisterMsg): ResponseMsg = {
        masterBlockingStub.RegisterWorker(request)
    }
}
