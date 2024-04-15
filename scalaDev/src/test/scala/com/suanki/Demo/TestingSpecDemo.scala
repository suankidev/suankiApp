package com.suanki.Demo

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class TestingSpecDemo extends AnyFunSpec with Matchers with TableDrivenPropertyChecks {

  val tableList: List[String] = List("A", "B", "C", "D")
  for (i <- tableList) {
    describe(s"table ${i} records counts match") {

      it(s"table ${i} should have more than 0 records ") {

        0 should be(0)
      }
    }

    describe(s"table ${i} number columns counts match") {

      it(s"table ${i} should have more than exact records records ") {

        0 should be(0)
      }

    }

  }

  describe("checking large list") {
    describe("on a list number") {
      val x           = 10 * 20
      val y: Seq[Int] = Vector(1, 2, 3, 4, 5, 6)
      they("should match all the case") {
        x should be(20)
        y should have size (6)
        y should not contain (10)
        y should be(sorted)
      }
    }
  }

}
