package State

import State.StateType
import org.apache.logging.log4j.scala.Logging

import Master.State

trait Mission {
    protected val id : Int
    protected val workerIndex : Int
    protected val stateType : StateType.Value
    protected val status : StateType.Value 

    def getId: Int = id
    def getWorkerIndex: Int = workerIndex
    def getStateType: StateType.Value = stateType
    def getStatus: StateType.Value = status
    def isTerminated: Boolean = {status == StateType.SUCCESS || status == StateType.FAIL}
    def waiting: Boolean = {status == StateType.WAIT}

}

class MissionSet( missions : Iterable[Mission] ) extends Logging {
    val missionSet: Set[Mission] = missions.toSet

    def allMissionComplete: Boolean = {
        missionSet.map(_.isTerminated).reduce((x,y) => x && y)
    }

    def getNumWaitingMissions: Int = missionSet.count(! _.isTerminated)

    def allMissionSuccess: Boolean = {
        missionSet.map(_.)
    }
}

object MissionHandler extends Logging {
}