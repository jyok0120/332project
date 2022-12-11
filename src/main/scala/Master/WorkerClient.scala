package master

import io.grpc.{ManagedChannel, ManagedChannelBuilder, StatusRuntimeException}

import org.apache.logging.log4j.scala.Logging

import Communicate.network.{MasterWorkerServiceGrpc, SortDataMsg, ResponseMsg, SampleDataMsg, SampleArrayMsg}


class WorkerClient( host : String, port : Int) extends Logging{
    private val workerChannel = ManagedChannelBuilder.forAddress(host,port).usePlaintext.build
    private val workerBlockingStub = MasterWorkerServiceGrpc.blockingStub(workerChannel)

    def sortingData(sortData: SortDataMsg): ResponseMsg = {
        try {
            logger.error("Check sort data message " + sortData)
            val response = workerBlockingStub.sortingData(sortData)
            response
        } catch {
            case e: StatusRuntimeException =>
                //logger.error(s"RPC failed: ${e.getStatus.toString}")
                new ResponseMsg( ResponseMsg.ResponseType.ERROR )
        }
    }

    def sampleDataResponse(sampleData: SampleDataMsg): SampleArrayMsg = {
         try {
            logger.error("Check sample data message " + sampleData)
            val response = workerBlockingStub.sortingData(sampleData)
            response
        } catch {
            여긴 어떡해야하나
            //case e: StatusRuntimeException =>
                //logger.error(s"RPC failed: ${e.getStatus.toString}")
                //new ResponseMsg( ResponseMsg.ResponseType.ERROR )
        }
    }
}