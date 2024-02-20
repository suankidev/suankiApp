package com.suanki

class TestClass extends UnitSpec {

  def matcherOne(): Unit = {

    it should ("match with result atest") in {
      val a = "test"
      a should equal("atest")
    }

    it should ("match with result test") in {
      val a = "test"
      a should equal("test")
    }

    it should ("match with result test1") in {
      val a = "test1"
      a should equal("test1")
    }
  }

  def matcherTwo() = {
    it should ("match with result testa") in {
      val a = "test"
      a should equal("testa")
    }

    it should ("get some df") in {

      getSparkSession.range(5).head(1)(0).asInstanceOf[Int] should be(0)
    }
  }

  matcherOne()
  matcherTwo()

}
