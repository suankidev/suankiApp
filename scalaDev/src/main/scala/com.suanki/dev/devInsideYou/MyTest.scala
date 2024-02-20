package com.suanki.tutorials.devInsideYou

class TestClass {
  var classVarOne: Int = 0
  var total: Int       = 0

  def add(): Unit = {
    total = total + classVarOne + 10
  }
}
object MyTest extends App {

  val testClass = new TestClass

  testClass.add()

  println(testClass.total)
  testClass.add()
  println(testClass.total)
  testClass.add()
  println(testClass.total)

}
