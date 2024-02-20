package com.suanki.tutorials.functionalp

object HigherOrderFunctions extends App {

  val superFunction: (Int, Int) => Int = (x: Int, y: Int) => x + y
  val superFunctionOne                 = (x: Int, y: Int) => (z: Int) => x + y + z

  println(superFunction(10, 20))
  println(superFunctionOne(10, 20)(30))

  // HOF  i.g  flatmap ,map,filter
  def ntimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else ntimes(f, n - 1, f(x))

  def plusOne = (x: Int) => x + 1

  println(ntimes(plusOne, 5, 1))

  def ntimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n < 0) (x: Int) => x else (x: Int) => ntimesBetter(f, n - 1)(f(x))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3                            = superAdder(3)
  println(add3(4))
  println(superAdder(3)(4))

  // function with multiple parameter lists

  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFromatter: (Double => String) = curriedFormatter("%4.8f")
  val preciseFromatter: (Double => String)  = curriedFormatter("%10.8f")

  println(standardFromatter(Math.PI))
  println(preciseFromatter(Math.PI))

}
