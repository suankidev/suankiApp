package com.suanki.tutorials

/** objectpackage name is same as main package
  *
  * universal classes and method resides in this only one package object can be created in package
  *
  * *
  */

package object Exercise {

  def sayHello: Unit = println("Hello from ")
  val SPEED_OF_LIGHT = 3e10 - 8

  def show() = {
    val st = "=" * 50
    println(s"${Console.GREEN} ${st} ${Console.RESET}")
  }
}
