package com.suanki.tutorials.devInsideYou.visibility

/** private[this] //only accessible to current object private //only instance of current object protected[this] //only
  * current instatnce and the subclass instance protexte //any instance of the current class and any instatnce of any
  * sub class
  *
  * final on a class mean that it can't be extended final //on trait not allowed final //on any member means can't ve
  * overwrittend
  *
  * abstract can't be instantiated abstrace //on any member is not allowed
  */

// example
class A {

  private[A] def foo(): Unit       = println("I'm foo , only accessible to A")
  protected[this] def foo1(): Unit = println("I'm foo , only accessible to A")

  def callingFoo: Unit = foo()
}

class B extends A {}

object VisibilityTest {

  def main(args: Array[String]): Unit = {
    val a = new A
    val b = new B
//    a.foo()
    a.callingFoo
//    b.foo1()
  }

}
