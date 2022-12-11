package master

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

import worker.WorkerStorage
import state.StateStatus

object Main extends Logging{
  def main(args: Array[String]): Unit = {

    MasterArgument.setWorkerNum(args)

    logger.info("Master start")
    // Start Networking Service
    MasterServer.startserver

    // Check Master - Worker register
    logger.info("Waiting for workers")
    
    val workerRegisterSuccess = Future {
      while(WorkerStorage.getWorkerNumber < MasterArgument.NumWorker) // This number refers to maximum worker num
      {
        Thread.sleep(3000)
      }
    }
    val workerRegisterSuccessWait = Await.result(workerRegisterSuccess, Duration.Inf)

    // Master's state machine
    var stateStatus: StateStatus.Value = StateStatus.SUCCESS

    val divideState = new DivideState
    stateStatus = divideState.waitWorkerTerminate
    logger.error("divide in to block")

    if(stateStatus == StateStatus.SUCCESS)
    {
      val sortingState = new SortState
      stateStatus = sortingState.waitWorkerTerminate
      logger.error("Sort state has done")
    }
    /*
    if(stateStatus == StateStatus.SUCCESS)
    {
      val samplingState = new SamplingState
      stateStatus = samplingState.waitWorkerTerminate
      logger.error("Sample state has done")
    }

    
    if(stateStatus == StateStatus.SUCCESS)
    {
      val partitionState = new PartitionState
      stateStatus = partitionState.waitWorkerTerminate
      logger.error("Parition state has done")
    }
    
    if(stateStatus == StateStatus.SUCCESS)
    {
      val shuffleState = new ShuffleState
      stateStatus = shuffleState.waitWorkerTerminate
      logger.error("Shuffle state has done")
    }

    if(stateStatus == StateStatus.SUCCESS)
    {
      val mergeState = new MergeState
      stateStatus = mergeState.waitWorkerTerminate
      logger.error("Merge state has done")
    }*/

    //val terminateState = new TerminateState
    //stateStatus = terminateState.waitWorkerTerminate
    //logger.error("Master has terminated")
    

    // End Networking Service
    MasterServer.stopserver
    logger.error("Master finish")
  }
}