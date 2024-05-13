package com.suanki.dev.typeSystem.genericType

class Invarient {

  lazy val invarient: Unit = {

    // list

    val numList: List[Int] = List(5, 6, 7)
    val anyList: List[Any] =
      numList // b/c List[Int] is covariant of List[Any], but thats not the case with Set they are invariant

    val numSet: Set[Int] = Set(4, 5, 6)
//    val anySet:Set[Any] = numSet

  }
}
