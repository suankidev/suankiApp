package com.suanki.dev.devInsideYou

//https://github.com/scopt/scopt/blob/develop/README.md
//https://dkbalachandar.wordpress.com/2018/10/13/how-to-use-scopthttps-github-com-scopt-scopt/
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
