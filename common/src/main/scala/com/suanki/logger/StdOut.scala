package com.suanki.logger

object StdOut {

  implicit class Printer(str: Any) {

    lazy val show: Unit = println(str)
  }
}
