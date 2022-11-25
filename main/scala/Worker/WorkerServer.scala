package Worker

import scala.concurrent.{ExecutionContext, Future}
import io.grpc.{ManagedChannel, ManagedChannelBuilder, StatusRuntimeException}

import Network.{ServerBase, ServerInterface}
import Network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg}

object WorkerServer extends ServerInterface{

}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService {

}

class WorkerClient( ip : String, port : Int) {

}
