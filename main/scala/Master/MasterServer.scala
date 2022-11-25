package Master

import scala.concurrent.{ExecutionContext, Future}

import Network.{ServerBase, ServerInterface}
import Network.{MasterWorkerServiceGrpc, RegisterMsg, ResponseMsg}

object MasterServer extends ServerInterface {

}

private class MasterWorkerServiceImpl extends MasterWorkerServiceGrpc.MasterWorkerService {

}


