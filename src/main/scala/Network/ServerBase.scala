package Network

import org.apache.logging.log4j.scala.Logging

import io.grpc.{Server, ServerBuilder, ServerServiceDefinition}

trait ServerInterface {
  val server : Server
  def startserver () = {server.start()}
  def stopserver () = {server.stop()}
}

class ServerBase ( serverService : ServerServiceDefinition,
                   serverName : String,
                   serverPort : Int) {

  self => private[this] var server: Server = null

  def start(): Unit = {
    server = ServerBuilder.forPort(serverPort).addService(serverService).build.start
    logger.info(s"$serverName has started, listening on " + serverPort)
    sys.addShutdownHook {
      logger.info(s"shutting down $serverName since JVM is shutting down")
      self.stop()
      logger.info(s"$serverName has shutdown")
    }
  }

  def stop(): Unit = {
    if (server != null) {
      server.shutdown()
      logger.info(s"$serverName has shutdown")
    }
  }

   def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }

}