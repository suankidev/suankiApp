package com.suanki.tutorials.devInsideYou.ExceptionsInScala

object ExceptionPartTwo {

  def main(args: Array[String]): Unit = {
    println("=" * 50)

//    println(null == null)  //== does check if not null and then call toString
//    println(null eq null)
//    println(null ne null)
//    println(null.asInstanceOf[Any])
//    println(null.isInstanceOf[Any])

//    println(null equals null)
    // Some and Option

    require(1 == 1)
    assert(1 == 1)
    assume(1 == 1)

    1 == 1 ensuring (_ == true)

    def fancyLogic(x: Int): Int = {
      x + 9
    } ensuring (_ > 0)

//    sys.error("boom")
//    throw new RuntimeException("boom")

    println("=" * 50)
  }

}
