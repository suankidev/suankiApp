package com.suanki.dev.appliedOne

object FunctionAndClosure {

  object Code {

    def myMethod(a: Int): String = s"hello $a"

    def apply(x: Int): String = s"$x"
  }

  def code: Unit = {

    val functionOne: (Int, Int) => Int = (a, b) => a * b
    val functionOneOtherWay            = (a: Int, b: Int) => a * b
    val functionOneHow = new Function2[Int, Int, Int] {
      override def apply(v1: Int, v2: Int): Int = v1 * v2
    }

    println(functionOne(2, 4))
    println(functionOneHow(2, 4))
    println(functionOneOtherWay(2, 4))
    println(functionOne.apply(2, 4))
    println(Code(10))

    val finOne    = functionOne.curried
    val finOneTup = functionOne.tupled
    println(finOne(2)(5))
    println(finOneTup(2, 5))

    def getVal(x: String): Int => String = { (y: Int) =>
      x * y
    }

    println(getVal("hello ")(5))

    //

    val myList: List[Int] = List(2, 4, 2, 3, 5, 9, 17, 3, 5, 9, 3, 4)

    println(myList.partition(x => x % 4 == 0))
    println(myList.span(x => x % 4 == 0))

    // hof

    def compare(x: Int, compreTo: (Int, Int) => Int): Unit = {
      if (x == compreTo(x + 1, x % 2)) {
        println("not true")
      }
    }

    compare(4, (a, b) => a + b)
    compare(4, _ + _)               // placeholder
    compare(4, (_: Int) + (_: Int)) // placeholder

    // partial funcion

    val fullOne: (Int, Int, Int) => Int = (x, y, z) => x + y + z
    val partOne                         = fullOne(4, _, 5)
    val partOneAll                      = fullOne(_, _, _)
    println(partOne(5))
    println(partOneAll(1, 2, 3))

    // all closure are function , but not all functional listeral are closure

    val incBy1    = (x: Int) => x + 1    // not a closure
    val more      = 10
    val incByMore = (x: Int) => x + more // a closure

    incBy1(10)
    incByMore(10)

    // any block of code
    val rslt: PartialFunction[Int, Int] = {
      case x: Int if x > 0 => x + x
      case x               => x * -1
    }
    println(rslt(5))

  }

}
