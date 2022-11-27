// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package Communicate.network

/** @param data
  *   Same as up part
  */
@SerialVersionUID(0L)
final case class ShuffleDataMsg(
    worker: _root_.scala.Predef.String = "",
    data: _root_.scala.Predef.String = "",
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[ShuffleDataMsg] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = worker
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
        }
      };
      
      {
        val __value = data
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, __value)
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
        val __v = worker
        if (!__v.isEmpty) {
          _output__.writeString(1, __v)
        }
      };
      {
        val __v = data
        if (!__v.isEmpty) {
          _output__.writeString(2, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def withWorker(__v: _root_.scala.Predef.String): ShuffleDataMsg = copy(worker = __v)
    def withData(__v: _root_.scala.Predef.String): ShuffleDataMsg = copy(data = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = worker
          if (__t != "") __t else null
        }
        case 2 => {
          val __t = data
          if (__t != "") __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PString(worker)
        case 2 => _root_.scalapb.descriptors.PString(data)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: Communicate.network.ShuffleDataMsg.type = Communicate.network.ShuffleDataMsg
    // @@protoc_insertion_point(GeneratedMessage[Communicate.ShuffleDataMsg])
}

object ShuffleDataMsg extends scalapb.GeneratedMessageCompanion[Communicate.network.ShuffleDataMsg] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[Communicate.network.ShuffleDataMsg] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): Communicate.network.ShuffleDataMsg = {
    var __worker: _root_.scala.Predef.String = ""
    var __data: _root_.scala.Predef.String = ""
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __worker = _input__.readStringRequireUtf8()
        case 18 =>
          __data = _input__.readStringRequireUtf8()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    Communicate.network.ShuffleDataMsg(
        worker = __worker,
        data = __data,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[Communicate.network.ShuffleDataMsg] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      Communicate.network.ShuffleDataMsg(
        worker = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        data = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = NetworkProto.javaDescriptor.getMessageTypes().get(7)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = NetworkProto.scalaDescriptor.messages(7)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = Communicate.network.ShuffleDataMsg(
    worker = "",
    data = ""
  )
  implicit class ShuffleDataMsgLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, Communicate.network.ShuffleDataMsg]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, Communicate.network.ShuffleDataMsg](_l) {
    def worker: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.worker)((c_, f_) => c_.copy(worker = f_))
    def data: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.data)((c_, f_) => c_.copy(data = f_))
  }
  final val WORKER_FIELD_NUMBER = 1
  final val DATA_FIELD_NUMBER = 2
  def of(
    worker: _root_.scala.Predef.String,
    data: _root_.scala.Predef.String
  ): _root_.Communicate.network.ShuffleDataMsg = _root_.Communicate.network.ShuffleDataMsg(
    worker,
    data
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[Communicate.ShuffleDataMsg])
}
