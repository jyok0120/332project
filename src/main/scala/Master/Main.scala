package Master

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

import Master.{MasterServer, SortState, SamplingState, PartitionState, ShuffleState, MergeState, TerminateState}
import Worker.WorkerStorage

object Main extends Logging{
  def main(args: Array[String]): Unit = {
    logger.info("Master start")
    // Start Networking Service
    MasterServer.startserver

    // Check Master - Worker register
    logger.info("Waiting for workers")
    
    val workerRegisterSuccess = Future {
      while(WorkerStorage.getWorkerNumber < 1)
      {
        Thread.sleep(3000)
      }
    }
    val workerRegisterSuccessWait = Await.result(workerRegisterSuccess, Duration.Inf)
    
    enum stateType {
      SUCCESS = 0
      FAIL = 1
    }

    // Master's state machine
    val stateStatus = stateType.SUCCESS

    val sortingState = new SortState
    stateStatus = sortingState.waitWorkerTerminate
    logger.error("Sort state has done")

    if(stateStatus == stateType.SUCCESS)
    {
      val samplingState = new SamplingState
      stateStatus = samplingState.waitWorkerTerminate
      logger.error("Sample state has done")
    }

    if(stateStatus == stateType.SUCCESS)
    {
      val partitionState = new PartitionState
      stateStatus = partitionState.waitWorkerTerminate
      logger.error("Parition state has done")
    }

    if(stateStatus == stateType.SUCCESS)
    {
      val shuffleState = new ShuffleState
      stateStatus = shuffleState.waitWorkerTerminate
      logger.error("Shuffle state has done")
    }

    if(stateStatus == stateType.SUCCESS)
    {
      val mergeState = new MergeState
      stateStatus = mergeState.waitWorkerTerminate
      logger.error("Merge state has done")
    }

    val terminateState = new TerminateState
    stateStatus = terminateState.waitWorkerTerminate
    logger.error("Master has terminated")

    // End Networking Service
    MasterServer.stopserver
    logger.error("Master finish")
  }
}