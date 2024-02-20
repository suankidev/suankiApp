package com.suanki.dev.Exercise

object PlayGround extends App {

  println("=" * 50)

  val d = new Function[Int, (Int) => Int] {
    override def apply(v1: Int): Int => Int = new Function[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }

  println(d(10)(20))

}
