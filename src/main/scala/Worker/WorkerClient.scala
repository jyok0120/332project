package Worker

import io.grpc.{ManagedChannel, ManagedChannelBuilder, StatusRuntimeException}

import Communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg}

class WorkerClient( host : String, port : Int) {
    private val masterChannel = ManagedChannelBuilder.forAddress(host,port).usePlaintext.build
    private val masterBlockingStub = MasterWorkerServiceGrpc.blockingStub(masterChannel)
    
    def registerWorker(request: RegisterMsg): ResponseMsg = {
        try {
            val response = masterBlockingStub.registerWorker(request)
            response
        } catch {
            case e: StatusRuntimeException =>
                //logger.error(s"RPC failed: ${e.getStatus.toString}")
                new ResponseMsg( ResponseMsg.ResponseType.ERROR )
        }
    }
}
