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
    def isTerminated: Boolean = {status == StateType.SUCCESS || status == StateType.FAIL}
    def 이름 뭐라하냐: Boolean = {status != StateType.WAIT}

}

class MissionSet() extends Logging {

}

object MissionHandler extends Logging {
    private var currentState: State = null
}