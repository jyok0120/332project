ThisBuild / scalaVersion := "2.12.17"


libraryDependencies ++= Seq(
    "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
)

libraryDependencies += "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0"
  
Compile / PB.targets := Seq(
scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)




