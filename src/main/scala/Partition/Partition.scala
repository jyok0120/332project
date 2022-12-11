package partition

import utils.FileIoUtils
import java.io.File


class Partition(_partitionName: String) extends Serializable {
    val partitionName: String = _partitionName

    val partitionRecordNumber: Int = {
        val partitionFile = new File(_partitionName)
        if (partitionFile.exists && partitionFile.isFile) {
            FileIoUtils.getLinesFromFile(_partitionName)
        } else {
            0
        }
    }
}