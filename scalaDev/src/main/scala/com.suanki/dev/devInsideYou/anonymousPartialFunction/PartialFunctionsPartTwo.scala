package com.suanki.tutorials.devInsideYou.anonymousPartialFunction

object PartialFunctionsPartTwo {

  def code(args: Array[String]): Unit = {

    /** defining our own partial function
      * @tparam Input
      * @tparam Output
      */

    val partialFunction3: PartialFunction[Int, String] = new PartialFunction[Int, String] {
      override def apply(name: Int): String = if (name > 4) {
        name.toString
      } else
        sys.error("Operaiton Not supported:you should call isDefined at before calling apply")

      override def isDefinedAt(x: Int): Boolean = {
        if (x > 4) true else false
      }
    }

    // type alias for partial function
    type ~>[-Input, +Output] = PartialFunction[Int, String]

    val partialFunction2: ~>[Int, String] = {
      case argument if argument == 4 =>
        s"${Console.MAGENTA} ${argument.toString} ${Console.RESET}"
    }

    val partialFunction1: Int ~> String = {
      case argument if argument == 4 =>
        s"${Console.MAGENTA} ${argument.toString} ${Console.RESET}"
    }

    // above casee can be written as
//    case argument @ 4 =>
//    case 4 =>

    val myRange = 1 to 10

    myRange collect partialFunction2 foreach println

  }
}
