package utils

import scala.collection.mutable
import scala.math._
import org.apache.logging.log4j.scala.Logging
import block.Block

object BlockUtils extends Logging {
    def makeBlockOffset(block: Block, partSize: Int): Array[(Int, Int)] = {
        val partInitOffsets = 1 to block.blockRecordNumber by partSize
        val partEndOffsets = partInitOffsets.map(offset => min(offset + partSize - 1, block.blockRecordNumber))
        
        partInitOffsets.zip(partEndOffsets).toArray
    }
}