package Worker

import io.grpc.{ManagedChannel, ManagedChannelBuilder, StatusRuntimeException}

import Communicate.network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg}

class WorkerClient( host : String, port : Int) {
    private val masterChannel = ManagedChannelBuilder.forAddress(host,port).usePlaintext.build
    private val masterBlockingStub = MasterWorkerServiceGrpc.blockingStub(masterChannel)
    
    def registerWorker(request: RegisterMsg): ResponseMsg = {
        val response = masterBlockingStub.registerWorker(request)
        response
    }
}
