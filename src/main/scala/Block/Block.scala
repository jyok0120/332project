package block

import utils.FileIoUtils
import java.io.File


class Block(_blockName: String) extends Serializable {
    val blockName: String = _blockName

    val blockRecordNumber: Int = {
        val blockFile = new File(_blockName)
        if (blockFile.exists && blockFile.isFile) {
            FileIoUtils.getNumLinesInFile(_blockName)
        } else {
            0
        }
    }
}