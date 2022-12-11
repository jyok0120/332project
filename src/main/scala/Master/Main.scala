package Master

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

<<<<<<< HEAD
import Master.MasterServer
import Worker.WorkerStorage
=======
import Master.{MasterServer, SortState, SamplingState, PartitionState, ShuffleState, MergeState, TerminateState}
import Worker.WorkerStorage
import Network.StateType
>>>>>>> c82d93fe43f98ef7ad13a90dd9b67d3713999e80

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
<<<<<<< HEAD
    
    val workerRegisterSuccessWait = Await.result(workerRegisterSuccess, Duration.Inf)
    
=======
    val workerRegisterSuccessWait = Await.result(workerRegisterSuccess, Duration.Inf)

>>>>>>> c82d93fe43f98ef7ad13a90dd9b67d3713999e80
    // Master's state machine
    var stateStatus: StateType.Value = StateType.SUCCESS

    val sortingState = new SortState
    stateStatus = sortingState.waitWorkerTerminate
    logger.error("Sort state has done")

    if(stateStatus == StateType.SUCCESS)
    {
      val samplingState = new SamplingState
      stateStatus = samplingState.waitWorkerTerminate
      logger.error("Sample state has done")
    }

    if(stateStatus == StateType.SUCCESS)
    {
      val partitionState = new PartitionState
      stateStatus = partitionState.waitWorkerTerminate
      logger.error("Parition state has done")
    }

    if(stateStatus == StateType.SUCCESS)
    {
      val shuffleState = new ShuffleState
      stateStatus = shuffleState.waitWorkerTerminate
      logger.error("Shuffle state has done")
    }

    if(stateStatus == StateType.SUCCESS)
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
<<<<<<< HEAD
    logger.info("Master finish")
    println("Master finish")
=======
    logger.error("Master finish")
>>>>>>> c82d93fe43f98ef7ad13a90dd9b67d3713999e80
  }
}