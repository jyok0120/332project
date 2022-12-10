package worker

import io.grpc.{ManagedChannel, ManagedChannelBuilder, StatusRuntimeException}

import org.apache.logging.log4j.scala.Logging

import communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg}

class MasterStub( host : String, port : Int) extends Logging{
    private val masterChannel = ManagedChannelBuilder.forAddress(host,port).usePlaintext.build
    private val masterBlockingStub = MasterWorkerServiceGrpc.blockingStub(masterChannel)
    
    def registerWorker(request: RegisterMsg): ResponseMsg = {
        try {
            logger.error("Check request message " + request)
            val response = masterBlockingStub.registerWorker(request)
            response
        } catch {
            case e: StatusRuntimeException =>
                //logger.error(s"RPC failed: ${e.getStatus.toString}")
                new ResponseMsg( ResponseMsg.ResponseType.ERROR )
        }
    }
}
