ThisBuild / scalaVersion := "2.12.17"


libraryDependencies ++= Seq(
    "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
)

libraryDependencies += "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.11.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.19.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.19.0"  

javaOptions += "-Dlog4j.configurationFile=conf/log4j2.xml"

Compile / PB.targets := Seq(
scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)

lazy val master = (project in file("./Master"))
lazy val worker = (project in file("./Worker"))

mainClass := Some("Master.Main")

//mainClass in (Compile, master) := Some("Master.MasterMain")
//mainClass in (Compile, worker) := Some("Worker.WorkerMain")



