package com.suanki.dev.basics

object Recursions extends App {

  def aFactorial(n: Int): Int = {

    if (n <= 1) 1
    else {
      println(
        "computing factorial of " + n + "- I firrst need factorail of " + (n - 1)
      )
      val result = n * aFactorial(n - 1)
      println("computed factorial of" + n + "rsult is" + result)
      result
    }
  }

  // aFactorial(5)

  // tailrec

  def aFactorialTail(n: Int): BigInt = {
    @scala.annotation.tailrec
    def loop(x: Int, acc: BigInt): BigInt = {
      if (x <= 1) acc
      else loop(x - 1, x * acc)
    }

    loop(n, 1) // TAILE RECIRSION= use recrsinve call as last expression
  }

  /** aFactorila(5)
    *
    * loop(10,1) -> acc =10 ,x =10 loop(9,10*9) --> acc = 90, x =9
    */

  def checkIfPrime(n: Int, acc: Boolean = true, incr: Int): Boolean = {
    if (incr <= 1) acc
    else if (!acc) acc
    else {
      checkIfPrime(n, if (n % incr == 0) false else true, incr - 1)
    }
  }

  println(checkIfPrime(18, true, 4))
  println(checkIfPrime(19, true, 4))

  // Canctenate string

  def concatenateTailrec(name: String, n: Int): String = {
    @scala.annotation.tailrec
    def loop(x: String, stop: Int, acc: String): String = {
      if (stop <= 0) acc
      else
        loop(x, stop - 1, acc + x)

    }

    loop(name, n, "")
  }

  println(concatenateTailrec("sujeet-", 5))

}
