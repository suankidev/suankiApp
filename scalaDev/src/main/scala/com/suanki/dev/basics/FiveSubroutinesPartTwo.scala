package com.suanki.dev.basics

import com.suanki.dev.Exercise.show

import scala.annotation.tailrec
object FiveSubroutinesPartTwo {

  def main(args: Array[String]): Unit = {
    show()

    def getCurrentTime   = java.time.LocalDateTime.now.getSecond
    var currentIteration = 1

    // method
//    println(method2)

    def method: String = {

      if (getCurrentTime % 5 != 0) {
        println(getCurrentTime)
        method
      } else
        "done"
    }

    def method2: String = {

      if (currentIteration % 5 != 0) {
        println(currentIteration)
        currentIteration += 1
        method
      } else
        "done"
    }

//    println(method3)
    def method3: String = {
      var currentIteration = 1

      def loop: String = {
        if (currentIteration % 5 != 0) {
          println(currentIteration)
          currentIteration += 1
          loop
        } else
          "done"
      }

      loop
    }

    // method3 and method4 look same but we are bounding currentIteration to the loop
    // method4 : here we are managing memory and stack ourself, which is tailrec
    println(method4)

    def method4: String = {
      @tailrec
      def loop(currentIteration: Int): String = {
        if (currentIteration % 5 != 0) {
          println(currentIteration)
          loop(currentIteration + 1)
        } else
          "done"
      }
      loop(1)
    }

    // factorial
    println(s"factorial of 5 is ${aFactorial(5)}")

    def aFactorial(num: Int): Int = {
      @tailrec
      def loop(num: Int, acc: Int): Int = {
        if (num == 0)
          1
        else if (num == 1)
          acc
        else
          loop(num - 1, acc * num)
      }
      loop(num, 1)
    }

    // single line factorial

    def fact: Int => Int = x => if (x == 0) 1 else x * fact(x - 1)

    def fact1: (Int, Int) => Int = (num, acc) => {
      if (num == 0) 1
      else if (num == 1) acc
      else fact1(num - 1, acc * num)
    }

    fact1(5, 1)

//    println(fact(5))

    // then
    show()
  }

}
