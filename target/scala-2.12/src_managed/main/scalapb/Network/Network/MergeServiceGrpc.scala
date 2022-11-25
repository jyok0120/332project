// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package Network.Network


object MergeServiceGrpc {
  val METHOD_REQUEST_MERGE: _root_.io.grpc.MethodDescriptor[Network.Network.RegisterMsg, Network.Network.ResponseMsg] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Network.MergeService", "RequestMerge"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Network.Network.RegisterMsg])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Network.Network.ResponseMsg])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(Network.Network.NetworkProto.javaDescriptor.getServices().get(4).getMethods().get(0)))
      .build()
  
  val METHOD_TERMINATE_MERGE: _root_.io.grpc.MethodDescriptor[Network.Network.ReportMsg, Network.Network.ResponseMsg] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Network.MergeService", "TerminateMerge"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Network.Network.ReportMsg])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[Network.Network.ResponseMsg])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(Network.Network.NetworkProto.javaDescriptor.getServices().get(4).getMethods().get(1)))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("Network.MergeService")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(Network.Network.NetworkProto.javaDescriptor))
      .addMethod(METHOD_REQUEST_MERGE)
      .addMethod(METHOD_TERMINATE_MERGE)
      .build()
  
  trait MergeService extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = MergeService
    def requestMerge(request: Network.Network.RegisterMsg): scala.concurrent.Future[Network.Network.ResponseMsg]
    def terminateMerge(request: Network.Network.ReportMsg): scala.concurrent.Future[Network.Network.ResponseMsg]
  }
  
  object MergeService extends _root_.scalapb.grpc.ServiceCompanion[MergeService] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[MergeService] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = Network.Network.NetworkProto.javaDescriptor.getServices().get(4)
    def scalaDescriptor: _root_.scalapb.descriptors.ServiceDescriptor = Network.Network.NetworkProto.scalaDescriptor.services(4)
    def bindService(serviceImpl: MergeService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
      _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
      .addMethod(
        METHOD_REQUEST_MERGE,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[Network.Network.RegisterMsg, Network.Network.ResponseMsg] {
          override def invoke(request: Network.Network.RegisterMsg, observer: _root_.io.grpc.stub.StreamObserver[Network.Network.ResponseMsg]): _root_.scala.Unit =
            serviceImpl.requestMerge(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .addMethod(
        METHOD_TERMINATE_MERGE,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[Network.Network.ReportMsg, Network.Network.ResponseMsg] {
          override def invoke(request: Network.Network.ReportMsg, observer: _root_.io.grpc.stub.StreamObserver[Network.Network.ResponseMsg]): _root_.scala.Unit =
            serviceImpl.terminateMerge(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .build()
  }
  
  trait MergeServiceBlockingClient {
    def serviceCompanion = MergeService
    def requestMerge(request: Network.Network.RegisterMsg): Network.Network.ResponseMsg
    def terminateMerge(request: Network.Network.ReportMsg): Network.Network.ResponseMsg
  }
  
  class MergeServiceBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[MergeServiceBlockingStub](channel, options) with MergeServiceBlockingClient {
    override def requestMerge(request: Network.Network.RegisterMsg): Network.Network.ResponseMsg = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_REQUEST_MERGE, options, request)
    }
    
    override def terminateMerge(request: Network.Network.ReportMsg): Network.Network.ResponseMsg = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_TERMINATE_MERGE, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): MergeServiceBlockingStub = new MergeServiceBlockingStub(channel, options)
  }
  
  class MergeServiceStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[MergeServiceStub](channel, options) with MergeService {
    override def requestMerge(request: Network.Network.RegisterMsg): scala.concurrent.Future[Network.Network.ResponseMsg] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_REQUEST_MERGE, options, request)
    }
    
    override def terminateMerge(request: Network.Network.ReportMsg): scala.concurrent.Future[Network.Network.ResponseMsg] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_TERMINATE_MERGE, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): MergeServiceStub = new MergeServiceStub(channel, options)
  }
  
  object MergeServiceStub extends _root_.io.grpc.stub.AbstractStub.StubFactory[MergeServiceStub] {
    override def newStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): MergeServiceStub = new MergeServiceStub(channel, options)
    
    implicit val stubFactory: _root_.io.grpc.stub.AbstractStub.StubFactory[MergeServiceStub] = this
  }
  
  def bindService(serviceImpl: MergeService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition = MergeService.bindService(serviceImpl, executionContext)
  
  def blockingStub(channel: _root_.io.grpc.Channel): MergeServiceBlockingStub = new MergeServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): MergeServiceStub = new MergeServiceStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = Network.Network.NetworkProto.javaDescriptor.getServices().get(4)
  
}