package com.suanki.dev.basics

import scala.annotation.tailrec

object ScalaFunction extends App {

  def aFunctions(a: String, b: Int): String = {

    a + " " + b
  }

  def aFunctions1: String = {
    "hello"
  }

  println(aFunctions("hello number", 20))
  println(aFunctions1)

  var count = 0

  def aRepeatedFunction(aString: String, n: Int): String = {
    count = count + 1
    println(s"running the repeated function ${count}")
    if (n == 1) "hello"
    else {
      println(aString)
      aString + aRepeatedFunction(aString, n - 1) // last line should be value
    }
  }

  println(aRepeatedFunction("test-", 5))

  // this produce side effects
  def aFuntionWithSideEffects(aString: String) = println(aString)

  // Auxialry function  , a function inside function

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n + 10)
  }

  println(aBigFunction(10))

  // Factorial function
  def aFactorial(n: Int): Int =
    if (n <= 0) 1
    else n * aFactorial(n - 1)

  println(aFactorial(5))
  // 5 * 4* 3* 2*1

  def aFibonacci(n: Int): Int = {
    if (n <= 2) 1
    else aFibonacci(n - 1) + aFibonacci(n - 2)
  }

  println("running fibnocci")
  println(aFibonacci(10))

  def isPrime(n: Int): Boolean = {

    def isPrimeUnit(t: Int): Boolean = {
      if (t <= 1) true
      else (n % t != 0 && isPrimeUnit(t - 1))
    }

    isPrimeUnit(n / 2)
  }

  // tailrecursion

  def aFactorialTail(n: Long): Long = {
    @tailrec
    def loop(acc: Long, n: Long): Long = {

      if (n == 1) acc
      else loop(acc = acc * n, n - 1)
    }

    loop(1, n)

  }

  // println(aFactorialTail((49)))

}
