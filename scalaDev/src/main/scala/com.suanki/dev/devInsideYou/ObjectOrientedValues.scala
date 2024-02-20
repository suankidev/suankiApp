package com.suanki.tutorials.devInsideYou

object ObjectOrientedValues {

  def main(args: Array[String]): Unit = {
    println("=" * 50)
    code(args)
    println("=" * 50)
  }

  def code(args: Array[String]): Unit = {

    type stringToString = String => String

    def f(g: Int => String, h: stringToString, i: String => Char): Unit = {
      val resultOfG: String = g(1337)
      val resultOfH: String = h(resultOfG)
      val resultOfI: Char   = i(resultOfH)
    }

    def g(number: Int): String = {
      Console.MAGENTA + number.toString.reverse + Console.RESET
    }

    def h(number: String): String = {
      number.toString.reverse
    }

    def i(string: String): Char = {
      if (string.nonEmpty) {
        string(0)
      } else
        '?'
    }

    f(g, h, i)

  }

  /** function g h and i belongs together see inversion of control.scala
    */
}
