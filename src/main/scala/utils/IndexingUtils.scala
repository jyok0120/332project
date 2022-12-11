package utils

object IndexingUtils {
    var currentBlockIndex = 0
    var currentMissionIndex = 0

    def createNewWorkerIndex(indices: Set[Int]): Int = if (indices.size == 0) 1 else indices.max + 1
    def createNewMissionIndex(): Int = {
        currentMissionIndex += 1
        currentMissionIndex
    }
    def createNewBlockIndex(): String = {
        currentBlockIndex += 1
        f"block_$currentMissionIndex%06d"
    }
}