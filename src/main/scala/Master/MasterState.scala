package master

import scala.concurrent.{Await, Future}
import org.apache.logging.log4j.scala.Logging
import scala.concurrent.duration.Duration


import state.{Mission, MissionSet, StateStatus, DivideMission, SortMission}
import communicate.network.{ResponseMsg, SortDataMsg, DivideMsg, SampleDataMsg}

import worker.WorkerStorage
import utils.SerializationUtils._
import utils.BlockUtils._
import utils.IndexingUtils._

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

class DivideState extends State{
    override def toString(): String = "DivideState"
    override def makeMissionSet(): MissionSet = {
        val missionList: Iterable[Mission] = {
            for( workerIndex <- BlockStorage.getWorkersIndex) yield {
                val blockList = BlockStorage.getBlockList(workerIndex)
                blockList.map ({
                    x => {
                        val offset: Array[(Int,Int)] = makeBlockOffset(x, 1000000)  //arbitrary size
                        val outputBlockNames: Array[String] = {1 to offset.size}.toArray.map(_ => createNewBlockIndex)
                        new DivideMission(workerIndex, StateStatus.WAIT, offset, outputBlockNames)
                    }
                }).toArray
            }
        }.flatten
        
        new MissionSet (missionList)
    }

    override def executeState(): Future[Int] = {
        while(! terminateCondition ) {
            
            for { mission : Mission <- missionSet.missionSet} yield {
                if( mission.waiting ) {
                    val divideMsg = new DivideMsg(serializeToByteString(mission))
                    val workerAddress: (String, Int) = WorkerStorage.getWorkerAddress(mission.getWorkerIndex)
                    
                    logger.error(s"${workerAddress} trying to execute divide state")

                    val workerClient = new WorkerClient(workerAddress._1, workerAddress._2)

                    val divideResponseMsg = workerClient.divideData(divideMsg)

                    divideResponseMsg.response match {
                        case ResponseMsg.ResponseType.SUCCESS => {
                            mission.setStatus(StateStatus.DONE)
                        }
                        case ResponseMsg.ResponseType.ERROR => {
                            mission.setStatus(StateStatus.WAIT)
                        }
                    }
                    logger.error(s"${workerAddress} has finished divide state")
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

class SortState extends State{
    override def toString(): String = "SortState"

    override def makeMissionSet(): MissionSet = {
        val missionList: Iterable[Mission] = {
            for( workerIndex <- BlockStorage.getWorkersIndex) yield {
                val blockList = BlockStorage.getBlockList(workerIndex)
                blockList.map ( x => {
                    new SortMission(workerIndex, StateStatus.WAIT, Array[String](createNewBlockIndex))
                }).toArray
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

    // 여기에 result handler가 있어야 하려나... block storage를 어케 해야할지 모르겠음
}

/*
class SamplingState extends State{
    override def toString(): String = "SampleState"

    override def makeMissionSet(): MissionSet = {

        val sampleRatio = 0.03

         val missionList: Iterable[Mission] = {
            for( workerIndex <- BlockStorage.getWorkersIndex) yield {
                val blockList = BlockStorage.getBlockList(workerIndex)
                blockList.map ({
                    x => (new SampleDataMission(workerIndex, StateStatus.WAIT, sampleRatio, 출력))
                }).toArray
            }
        }.flatten
        
        new MissionSet (missionList)
    }

    override def executeState(): Future[Int] = {
        while(! terminateCondition ) {
            
            for { mission : Mission <- missionSet.missionSet} yield {
                if( mission.waiting ) {
                    val sampleData = new SampleDataMsg(serializeToByteString(mission))
                    val workerAddress: (String, Int) = WorkerStorage.getWorkerAddress(mission.getWorkerIndex)
                    
                    logger.error(s"${workerAddress} trying to execute sample state")

                    val workerClient = new WorkerClient(workerAddress._1, workerAddress._2)

                    val sampleDataResponse = workerClient.sampleDataResponse(sampleData)

                    // sample array data를 가지고 어떻게 처리할 건가
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



class PartitionState extends State{

}

class ShuffleState extends State{

}

class MergeState extends State{

}

class TerminateState extends State{

}*/