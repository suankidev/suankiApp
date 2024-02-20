package com.suanki.tutorials.devInsideYou.scalaCollections

object SetInScala {

  def basicOfSeq(): Unit = {

    println(Seq(1, 2, 3))
    println(Seq(1, 3, 2))
    println(Seq(1, 3, 2, 1, 2)) // no duplicates

    println()

    println(Seq(1, 2, 3) == Seq(1, 2, 3, 1, 1)) // true, order doesn't matter

    println()

    println(Seq[Int]())     // empty sequence
    println(Seq.empty[Int]) // empty sequence

    println(s"""Set ===>""")

    println(Set(1, 2, 3))
    println(Set(1, 3, 2))
    println(Set(1, 3, 2, 1, 2))

    println()

    println(Set(1, 2, 3) == Set(1, 2, 3, 1, 1)) // true, order doesn't matter

    println(Set[Int]())     // empty sequence
    println(Set.empty[Int]) // empty sequence

  }
}
