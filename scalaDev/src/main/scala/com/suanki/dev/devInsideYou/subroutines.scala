package com.suanki.dev.devInsideYou

import scala.annotation.tailrec

object subroutines extends App {

  println("=" * 50)

  {

    { // string

      val myString = "Hello world"
      println(myString.equalsIgnoreCase("tes"))

      // val x = scala.io.StdIn.readInt()
//      val x = scala.io.StdIn.readLine("your favourite number:")
//      println(x)

      val f: Int => Int = _ + 5
      val g: Int => Int = _ + 4

//      val g:Int=>Int = x => x+5

      println(f(g(5)))

      def rightThenLeft(
          left: Int => Int,
          right: Int => Int
      ): Int => Int = { input =>
        left(right(input))
      }

      println(rightThenLeft(f, g)(10))

    }

    val t: Boolean = false && {
      println("evaluated only when first is true");
      true
    }

    val u: Boolean = true && {
      println("visible");
      true
    }

//    println(t)
    //  println(u)

    val v = ""

    if (v.isEmpty) {
      println("tst")
    } else
      println("no tst")

  }

  // premitive types

  val a: Byte = 1 // 1 Byte

  val b: Short = 1   // 2 byte
  val c: Char  = 'c' // 2 byte

  val d: Int  = 1 // 4 bytes
  val e: Long = 1 // 8 bytes

  val f: Float  = 0.1f // 4 bytes
  val g: Double = 0.1  // 8 bytes

  val h: Boolean = true // false

  val i: Unit = {} // ()

  {

    val x: Int => Int = x => x + 1

    val y: (Int => Int) = y => y + 1
    val z: (Int => Int) = _ + 1

    val i: (Int, Int) => Boolean = (left, right) => left == right
    val j: (Int, Int) => Boolean = _ == _

    // println(y(10))

    //    println(9.toBinaryString)
    //    println(9.toOctalString)
    //    println(15.toHexString)

    def toBinary(num: Int): String = {

      def loop(num: Int, acc: String = ""): String = {

        if (num < 1) acc
        else loop(num / 2, s"${num % 2}$acc")

      }

      loop(num)
    }

    // println(toBinary(9))
  }

  def method: Unit = {

    @tailrec
    def loop(itteration: Int): Unit = {
      if (itteration % 5 == 0) println(itteration)
      else loop(itteration + 1)
    }

    loop(2)
  }

  // method

  {
    @tailrec
    def factorial(num: Int, acc: Int = 1): Int = {
      if (num == 0 || num == 1) acc
      else factorial(num - 1, acc = acc * num)
    }

    // println(  factorial(5) )

    // print count of 2

    @tailrec
    def countOfTwo(num: Int, acc: Int = 1, incr: Int = 2): Unit = {
      if (acc > 10) {
        println()
        countOfTwo(num + 1, 1, incr + 1)
      } else if (incr > 10) println("End")
      else {
        print(s"${num * acc} ")
        countOfTwo(num, acc + 1, incr)
      }
    }

    // countOfTwo(2)

    def code =
      (first: Boolean, second: Boolean) => {
        println("this is subroutine")
        if (first) {
          println("this is true")
        } else {
          println("not sunny day")
        }
      }

    def code2(first: Boolean, second: Boolean): Unit = {
      println("this is subroutine")

      if (first) {
        println("this is true")
      } else {
        println("not sunny day")
      }
    }

    println("=" * 50)

  }
}
