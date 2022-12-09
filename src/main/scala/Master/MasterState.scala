package Master

import scala.concurrent.{Await, Future}
import org.apache.logging.log4j.scala.Logging
import scala.concurrent.duration.Duration

import Network.StateType

trait State {

    def executeState(): Future[Int] = {
        Future.successful(0) // 0 implies success. otherwise, failed
        // Future.successful(1) 
    }

    def waitWorkerTerminate(): StateType.Value = {

        val workerExitStatus: Int = Await.result(executeState, Duration.Inf)

        val stateStatus = workerExitStatus match {
            case 0 => {
                StateType.SUCCESS
            }
            case _ => {
                StateType.FAIL
            }
        }
        stateStatus
    }
}

class SortState extends State{
    
}

class SamplingState extends State{

}

class PartitionState extends State{

}

class ShuffleState extends State{

}

class MergeState extends State{

}

class TerminateState extends State{

}