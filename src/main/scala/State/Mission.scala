package state

import org.apache.logging.log4j.scala.Logging


abstract class Mission (missionWorkerIndex : Int,
                         missionStatus : StateStatus.Value, 
                         missionStateType : StateType.Value,
                         missionSampleRatio : Float,
                         missionOffset : Array[(Int,Int)],
                         missionInputBlock : Array[String],
                         misssionOutputBlock : Array[String] ) extends Serializable {

    protected val workerIndex : Int = missionWorkerIndex
    protected val stateType : StateType.Value = missionStateType
    protected var status : StateStatus.Value = missionStatus
    val inputBlock: Array[String] = missionInputBlock
    val outputBlock: Array[String] = misssionOutputBlock
    val offset: Array[(Int, Int)] = missionOffset
    val sampleRatio: Float = missionSampleRatio

    def getWorkerIndex: Int = workerIndex
    def getStateType: StateType.Value = stateType
    def getStatus: StateStatus.Value = status
    def setStatus(newStatus : StateStatus.Value): Unit = { status = newStatus}
    def isTerminated: Boolean = {status == StateStatus.SUCCESS || status == StateStatus.FAIL}
    def waiting: Boolean = {status == StateStatus.WAIT}

}

class MissionSet( missions : Iterable[Mission] ) extends Logging {
    val missionSet: Set[Mission] = missions.toSet

    def allMissionComplete: Boolean = {
        missionSet.map(_.isTerminated).reduce((x,y) => x && y)
    }

    def getNumWaitingMissions: Int = missionSet.count(! _.isTerminated)

    def allMissionSuccess: Boolean = {
        missionSet.map(_.getStatus == StateStatus.SUCCESS).reduce((x,y) => x && y)
    }
}

class DivideMission(missionWorkerIndex : Int, missionStatus : StateStatus.Value, 
                    missionOffset : Array[(Int,Int)], misssionOutputBlock : Array[String]) 
extends Mission (missionWorkerIndex, missionStatus, StateType.DIVIDE, 0, missionOffset, Array[String]("BlockMission"), misssionOutputBlock)
with Serializable{}


final class SortMission(missionWorkerIndex : Int, missionStatus : StateStatus.Value,
                    misssionOutputBlock : Array[String])  
extends Mission (missionWorkerIndex, missionStatus, StateType.SORT, 0, null, Array[String]("SortMission"), misssionOutputBlock)
with Serializable{}

//class SampleDataMission(missionWorkerIndex : Int, missionStatus : StateStatus.Value,
//                        missionSampleRatio : Float, misssionOutputBlock : Array[String]) 
//extends Mission (missionWorkerIndex, missionStatus, StateType.SAMPLE, null, missionSampleRatio, Array[String]("SampleMission"), misssionOutputBlock)
//with Serializable{}