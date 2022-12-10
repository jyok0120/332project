package state

object StateStatus extends Enumeration {
    val SUCCESS, FAIL, WAIT, DONE = Value
}

object StateType extends Enumeration {
    val SORT, SAMPLE, PARITION, SHUFFLE, MERGE,TERMINATE = Value
}