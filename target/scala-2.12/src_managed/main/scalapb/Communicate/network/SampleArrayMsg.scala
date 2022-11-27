// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package Communicate.network

@SerialVersionUID(0L)
final case class SampleArrayMsg(
    serializedsample: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[SampleArrayMsg] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = serializedsample
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBytesSize(1, __value)
        }
      };
      __size += unknownFields.serializedSize
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var __size = __serializedSizeMemoized
      if (__size == 0) {
        __size = __computeSerializedSize() + 1
        __serializedSizeMemoized = __size
      }
      __size - 1
      
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      {
        val __v = serializedsample
        if (!__v.isEmpty) {
          _output__.writeBytes(1, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def withSerializedsample(__v: _root_.com.google.protobuf.ByteString): SampleArrayMsg = copy(serializedsample = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = serializedsample
          if (__t != _root_.com.google.protobuf.ByteString.EMPTY) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PByteString(serializedsample)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: Communicate.network.SampleArrayMsg.type = Communicate.network.SampleArrayMsg
    // @@protoc_insertion_point(GeneratedMessage[Communicate.SampleArrayMsg])
}

object SampleArrayMsg extends scalapb.GeneratedMessageCompanion[Communicate.network.SampleArrayMsg] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[Communicate.network.SampleArrayMsg] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): Communicate.network.SampleArrayMsg = {
    var __serializedsample: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __serializedsample = _input__.readBytes()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    Communicate.network.SampleArrayMsg(
        serializedsample = __serializedsample,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[Communicate.network.SampleArrayMsg] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      Communicate.network.SampleArrayMsg(
        serializedsample = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.com.google.protobuf.ByteString]).getOrElse(_root_.com.google.protobuf.ByteString.EMPTY)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = NetworkProto.javaDescriptor.getMessageTypes().get(4)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = NetworkProto.scalaDescriptor.messages(4)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = Communicate.network.SampleArrayMsg(
    serializedsample = _root_.com.google.protobuf.ByteString.EMPTY
  )
  implicit class SampleArrayMsgLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, Communicate.network.SampleArrayMsg]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, Communicate.network.SampleArrayMsg](_l) {
    def serializedsample: _root_.scalapb.lenses.Lens[UpperPB, _root_.com.google.protobuf.ByteString] = field(_.serializedsample)((c_, f_) => c_.copy(serializedsample = f_))
  }
  final val SERIALIZEDSAMPLE_FIELD_NUMBER = 1
  def of(
    serializedsample: _root_.com.google.protobuf.ByteString
  ): _root_.Communicate.network.SampleArrayMsg = _root_.Communicate.network.SampleArrayMsg(
    serializedsample
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[Communicate.SampleArrayMsg])
}
