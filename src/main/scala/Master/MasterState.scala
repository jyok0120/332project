package master

import scala.concurrent.{Await, Future}
import org.apache.logging.log4j.scala.Logging
import scala.concurrent.duration.Duration

import state.{Mission, MissionSet, StateStatus, SortMission}
import Communicate.network.{ResponseMsg, SortDataMsg}
import worker.WorkerStorage
import utils.SerializationUtils._

trait State extends Logging{

    protected val missionSet: MissionSet = makeMissionSet()

    def terminateCondition: Boolean = missionSet.allMissionComplete
    def numWaitingMission: Int = missionSet.getNumWaitingMissions
    def successCondition: Boolean = missionSet.allMissionSuccess

    def makeMissionSet(): MissionSet

    def executeState(): Future[Int] = {
        Future.successful(0) // 0 implies success. otherwise, failed
        // Future.successful(1) 
    }

    def waitWorkerTerminate(): StateStatus.Value = {

        val workerExitStatus: Int = Await.result(executeState, Duration.Inf)

        val stateStatus = workerExitStatus match {
            case 0 => {
                StateStatus.SUCCESS
            }
            case _ => {
                logger.error(s"State ${this.toString} has failed")
                StateStatus.FAIL
            }
        }
        stateStatus
    }
}

class SortState extends State{

    override def makeMissionSet(): MissionSet = {
        val missionList: Iterable[Mission] = {
            for( workerIndex <- PartitionStorage.getWorkersIndex) yield {
                val partitionList = PartitionStorage.getPartitionList(workerIndex)
                partitionList.map {
                    x => (new SortMission(workerIndex, StateStatus.WAIT))
                }.toArray
            }
        }.flatten
        
        new MissionSet (missionList)
    }

    override def executeState(): Future[Int] = {

        while(! terminateCondition ) {
            
            for { mission : Mission <- missionSet.missionSet} yield {
                if( mission.waiting ) {
                    val sortMsg = new SortDataMsg(serializeToByteString(mission))
                    val workerAddress: (String, Int) = WorkerStorage.getWorkerAddress(mission.getWorkerIndex)
                    
                    logger.error(s"${workerAddress} trying to execute sort state")

                    val workerClient = new WorkerClient(workerAddress._1, workerAddress._2)

                    val sortResponseMsg = workerClient.sortingData(sortMsg)

                    sortResponseMsg.response match {
                        case ResponseMsg.ResponseType.SUCCESS => {
                            mission.setStatus(StateStatus.DONE)
                        }
                        case ResponseMsg.ResponseType.ERROR => {
                            mission.setStatus(StateStatus.WAIT)
                        }
                    }
                    logger.error(s"${workerAddress} has finished sort state")
                }
            }
            logger.error(s"${numWaitingMission} missions are waiting")
        }
        successCondition match {
            case true => Future.successful(0)
            case false => Future.successful(1)
        }
    }
}

/*
class SamplingState extends State{

}

class PartitionState extends State{

}

class ShuffleState extends State{

}

class MergeState extends State{

}

class TerminateState extends State{

}*/