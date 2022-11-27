// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package Communicate.network


object ShufflingServiceGrpc {
  val METHOD_REQUEST_SHUFFLING: _root_.io.grpc.MethodDescriptor[Communicate.network.ShuffleRequestMsg, Communicate.network.ResponseMsg] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Communicate.ShufflingService", "RequestShuffling"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Communicate.network.ShuffleRequestMsg])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Communicate.network.ResponseMsg])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(Communicate.network.NetworkProto.javaDescriptor.getServices().get(3).getMethods().get(0)))
      .build()
  
  val METHOD_SHUFFLE_DATA: _root_.io.grpc.MethodDescriptor[Communicate.network.ShuffleDataMsg, Communicate.network.ResponseMsg] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Communicate.ShufflingService", "ShuffleData"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Communicate.network.ShuffleDataMsg])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Communicate.network.ResponseMsg])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(Communicate.network.NetworkProto.javaDescriptor.getServices().get(3).getMethods().get(1)))
      .build()
  
  val METHOD_TERMINATE_SHUFFLING: _root_.io.grpc.MethodDescriptor[Communicate.network.ReportMsg, Communicate.network.ResponseMsg] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Communicate.ShufflingService", "TerminateShuffling"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Communicate.network.ReportMsg])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Communicate.network.ResponseMsg])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(Communicate.network.NetworkProto.javaDescriptor.getServices().get(3).getMethods().get(2)))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("Communicate.ShufflingService")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(Communicate.network.NetworkProto.javaDescriptor))
      .addMethod(METHOD_REQUEST_SHUFFLING)
      .addMethod(METHOD_SHUFFLE_DATA)
      .addMethod(METHOD_TERMINATE_SHUFFLING)
      .build()
  
  trait ShufflingService extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = ShufflingService
    def requestShuffling(request: Communicate.network.ShuffleRequestMsg): scala.concurrent.Future[Communicate.network.ResponseMsg]
    def shuffleData(request: Communicate.network.ShuffleDataMsg): scala.concurrent.Future[Communicate.network.ResponseMsg]
    def terminateShuffling(request: Communicate.network.ReportMsg): scala.concurrent.Future[Communicate.network.ResponseMsg]
  }
  
  object ShufflingService extends _root_.scalapb.grpc.ServiceCompanion[ShufflingService] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[ShufflingService] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = Communicate.network.NetworkProto.javaDescriptor.getServices().get(3)
    def scalaDescriptor: _root_.scalapb.descriptors.ServiceDescriptor = Communicate.network.NetworkProto.scalaDescriptor.services(3)
    def bindService(serviceImpl: ShufflingService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
      _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
      .addMethod(
        METHOD_REQUEST_SHUFFLING,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[Communicate.network.ShuffleRequestMsg, Communicate.network.ResponseMsg] {
          override def invoke(request: Communicate.network.ShuffleRequestMsg, observer: _root_.io.grpc.stub.StreamObserver[Communicate.network.ResponseMsg]): _root_.scala.Unit =
            serviceImpl.requestShuffling(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .addMethod(
        METHOD_SHUFFLE_DATA,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[Communicate.network.ShuffleDataMsg, Communicate.network.ResponseMsg] {
          override def invoke(request: Communicate.network.ShuffleDataMsg, observer: _root_.io.grpc.stub.StreamObserver[Communicate.network.ResponseMsg]): _root_.scala.Unit =
            serviceImpl.shuffleData(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .addMethod(
        METHOD_TERMINATE_SHUFFLING,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[Communicate.network.ReportMsg, Communicate.network.ResponseMsg] {
          override def invoke(request: Communicate.network.ReportMsg, observer: _root_.io.grpc.stub.StreamObserver[Communicate.network.ResponseMsg]): _root_.scala.Unit =
            serviceImpl.terminateShuffling(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .build()
  }
  
  trait ShufflingServiceBlockingClient {
    def serviceCompanion = ShufflingService
    def requestShuffling(request: Communicate.network.ShuffleRequestMsg): Communicate.network.ResponseMsg
    def shuffleData(request: Communicate.network.ShuffleDataMsg): Communicate.network.ResponseMsg
    def terminateShuffling(request: Communicate.network.ReportMsg): Communicate.network.ResponseMsg
  }
  
  class ShufflingServiceBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[ShufflingServiceBlockingStub](channel, options) with ShufflingServiceBlockingClient {
    override def requestShuffling(request: Communicate.network.ShuffleRequestMsg): Communicate.network.ResponseMsg = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_REQUEST_SHUFFLING, options, request)
    }
    
    override def shuffleData(request: Communicate.network.ShuffleDataMsg): Communicate.network.ResponseMsg = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_SHUFFLE_DATA, options, request)
    }
    
    override def terminateShuffling(request: Communicate.network.ReportMsg): Communicate.network.ResponseMsg = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_TERMINATE_SHUFFLING, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): ShufflingServiceBlockingStub = new ShufflingServiceBlockingStub(channel, options)
  }
  
  class ShufflingServiceStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[ShufflingServiceStub](channel, options) with ShufflingService {
    override def requestShuffling(request: Communicate.network.ShuffleRequestMsg): scala.concurrent.Future[Communicate.network.ResponseMsg] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_REQUEST_SHUFFLING, options, request)
    }
    
    override def shuffleData(request: Communicate.network.ShuffleDataMsg): scala.concurrent.Future[Communicate.network.ResponseMsg] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_SHUFFLE_DATA, options, request)
    }
    
    override def terminateShuffling(request: Communicate.network.ReportMsg): scala.concurrent.Future[Communicate.network.ResponseMsg] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_TERMINATE_SHUFFLING, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): ShufflingServiceStub = new ShufflingServiceStub(channel, options)
  }
  
  object ShufflingServiceStub extends _root_.io.grpc.stub.AbstractStub.StubFactory[ShufflingServiceStub] {
    override def newStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): ShufflingServiceStub = new ShufflingServiceStub(channel, options)
    
    implicit val stubFactory: _root_.io.grpc.stub.AbstractStub.StubFactory[ShufflingServiceStub] = this
  }
  
  def bindService(serviceImpl: ShufflingService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition = ShufflingService.bindService(serviceImpl, executionContext)
  
  def blockingStub(channel: _root_.io.grpc.Channel): ShufflingServiceBlockingStub = new ShufflingServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): ShufflingServiceStub = new ShufflingServiceStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = Communicate.network.NetworkProto.javaDescriptor.getServices().get(3)
  
}