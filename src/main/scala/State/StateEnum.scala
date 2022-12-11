package state

object StateStatus extends Enumeration {
    val SUCCESS, FAIL, WAIT, DONE = Value
}

object StateType extends Enumeration {
    val DIVIDE, SORT, SAMPLE, PARITION, SHUFFLE, MERGE,TERMINATE = Value
}