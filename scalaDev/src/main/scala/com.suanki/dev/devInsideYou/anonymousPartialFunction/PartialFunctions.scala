package com.suanki.tutorials.devInsideYou.anonymousPartialFunction

object PartialFunctions {

  def code(args: Array[String]): Unit = {

    val randomNumber: Int = scala.util.Random.nextInt(90)

    // total function
    val totalFunction: Function1[Int, String] = new Function1[Int, String] {
      override def apply(name: Int): String = name.toString

    }

    // partial function can specify which value won't accepts
    val partialFunction: PartialFunction[Int, String] = new PartialFunction[Int, String] {
      override def apply(name: Int): String = if (name > 4) {
        name.toString
      } else
        sys.error("Operaiton Not supported:you should call isDefined at before calling apply")

      override def isDefinedAt(x: Int): Boolean = {
        if (x > 4) true else false
      }
    }

    println(totalFunction(randomNumber))
//    println(partialFunction(randomNumber))

    // so it mean caller should check before calling partial function
    if (partialFunction.isDefinedAt(randomNumber)) {
      println(partialFunction(randomNumber))
    } else {
      println("not calling random at")
    }

    println(partialFunction(5))

    // uses of total funciton and partial functinos
    println("==>\n\n")
    val range = 1 to 10 // 1.to(10)

    val function: Int => Int = _ + 1

    range map function foreach println
    println("")
    range map totalFunction foreach println
    println("")
    // like predicate
    val predicate: Int => Boolean = _ == 4
    range filter predicate foreach println

    // collect do two thing map and filter at the same time
    range collect partialFunction foreach println // partial function  can do filter
    // 0/p 4 to 10

    println("testing map on partial function since partial function extend function1")
    println("===>")
//    range map partialFunction foreach (println)

  }
}
