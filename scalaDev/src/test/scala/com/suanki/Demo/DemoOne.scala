package com.suanki.Demo
import org.scalatest.funsuite._
import org.scalatest.matchers.should.Matchers

class DemoOne extends AnyFunSuite with Matchers {

  val num: List[Int] = (1 to 20).toList

  test(testName = "Filter a list") {
    val filtered = num.filter(_ > 15)
    assert(filtered == Seq(16, 17, 18, 19, 20))
  }

  test("summing a list") {
    num.sum should be(210)
    num.sum should be(211)
  }

  test("try something else")(pending)
}
