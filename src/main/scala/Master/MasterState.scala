package Master

import scala.concurrent.{Await, Future}
import org.apache.logging.log4j.scala.Logging
import scala.concurrent.duration.Duration

import State.StateType
import Communicate.network.{ResponseMsg, SortDataMsg}
import Worker.WorkerStorage
import Master.WorkerClient

trait State extends Logging{

    protected val missionSet: MissionSet = makeMissionSet()

    def makeMissionSet(): MissionSet

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
                logger.error(s"State ${this.toString} has failed")
                StateType.FAIL
            }
        }
        stateStatus
    }
}

class SortState extends State{

    override def makeMissionSet(): MissionSet = {

    }


    override def executeState(): Future[Int] = {
        for()
        private var condition = false
        // 여러 개의 워커에 대해 이게 성립하는지 확인하기 위해 for이나 while이 하나 더 필요할듯
        while(condition) {

            val sortMsg = new SortDataMsg(어떤 정보 넘겨주지)
            val workerAddress: (String, Int) = WorkerStorage.getWorkerAddress(이건 어디서 어케 가져오냐)
            
            logger.error(s"${workerAddress} trying to execute sort state")

            val sortResponseMsg = WorkerClient.sortData(sortMsg)

            sort.response match {
                case ResponseType.SUCCESS => {
                    condition = true
                }
                case ResponseType.ERROR => {
                    condition = false
                }
            }
        }
        logger.error(s"${workerAddress} has finished sort state")

        // result에 따라 future 값 넣기

    }
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