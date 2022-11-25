import org.apache.logging.log4j.scala.Logging


object Main {

  logger.info("Master start")
  // Start Networking Service
  MasterServer.startserver

  // Check Master - Worker register
  logger.info("Waiting for workers")
  
  // Might use future for waiting register
  

  // Master's state machine


  // End Networking Service
  MasterServer.stopserver
  logger.info("Master finish")

}