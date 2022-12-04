package Master

import scala.concurrent.{Await, Future}
import org.apache.logging.log4j.scala.Logging
import scala.concurrent.duration.Duration

trait State {
    enum stateType {
      SUCCESS = 0
      FAIL = 1
    }

    def executeState(): Future[Int]

    def waitWorkerTerminate(): Int = {
        // Worker에 신호를 보내야 함

        val workerExitStatus: Int = Await.result(executeState, Duration.Inf)

        val stateStatus = workerExitStatus match {
            case 0 => {
                logger.error("State ~~ has success")
                stateType.SUCCESS
            }
            case _ => {
                logger.error("State ~~~ has failed")
                stateType.FAIL
            }
        }
    }
}

class SortState extends State{
    override def executeState(): Future[Int] = {
        // While 모든 worker에서 sort가 끝날때까지

            // sort에 해당되는 grpc 메세지 호출

            // 메세지 response를 기준으로 NORMAL ERROR 구분

            // 위 값의 에러에 따라 특정 worker가 성공했는지 여부를 기록함
        
        // While loop이 종료될때까지 계속해서 thread sleep

        // 그 후, 결과 값에 따라 0 1 반환 (성공여부)
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