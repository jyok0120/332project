package Network

import org.apache.logging.log4j.scala.Logging

import io.grpc.{Server, ServerBuilder, ServerServiceDefinition}

trait ServerInterface {
}

class ServerBase ( serverService : ServerServiceDefinition,
                   serverName : String,
                   serverPort : Int) {

}
