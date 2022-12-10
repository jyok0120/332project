package Master

import io.grpc.{ManagedChannel, ManagedChannelBuilder, StatusRuntimeException}

import org.apache.logging.log4j.scala.Logging

import Communicate.network.{MasterWorkerServiceGrpc, StateMsg, ResponseMsg}


class WorkerClient( host : String, port : Int) extends Logging{
    private val workerChannel = ManagedChannelBuilder.forAddress(host,port).usePlaintext.build
    private val workerBlockingStub = MasterWorkerServiceGrpc.blockingStub(workerChannel)
    
    def stateTransitionWorker(request: StateMsg): ResponseMsg = {
        try {
            logger.error("Check request message " + request)
            val response = workerBlockingStub.stateTransitionWorker(request)
            response
        } catch {
            case e: StatusRuntimeException =>
                //logger.error(s"RPC failed: ${e.getStatus.toString}")
                new ResponseMsg( ResponseMsg.ResponseType.ERROR )
        }
    }
}