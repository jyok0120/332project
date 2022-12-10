package utils

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import com.google.protobuf.ByteString

object SerializationUtils {

    def serializeToByteArray(value: Any): Array[Byte] = {
        val stream: ByteArrayOutputStream = new ByteArrayOutputStream()
        val oos = new ObjectOutputStream(stream)
        oos.writeObject(value)
        oos.close
        stream.toByteArray
    }

     def serializeToByteString(value: Any): ByteString  = {
        ByteString copyFrom serializeToByteArray(value)
    }
}