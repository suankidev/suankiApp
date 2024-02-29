package com.suanki.dev.functionalp

object AnonymousFunctions extends App {

  // old
  val doubler = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 * 90
  }
  println(doubler(10))

  // new
  // both are same Anonymous function
  val doubleOne             = (x: Int) => x * 2
  val doubleTwo: Int => Int = x => x * 2

  def test: Int => Int = (x: Int) => x * 10
  println(doubleOne(10))
  println(test(10))

  // =============================>Final

  // LAMBDA
  val d              = (x: Int) => x * 2
  val d1: Int => Int = (x: Int) => x * 2

  // multiple parameters
  val m                     = (x: Int, y: Int) => x + y
  val m1: (Int, Int) => Int = (x: Int, y: Int) => x + y

//no params
  val p              = () => println("test")
  val p1: () => Unit = () => println("test")

  // careful
  println(p)   // function itself
  println(p()) // function call

  val inc: Int => Int              = _ + 1 // equivalent to x => x +1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (x,y) => x + y

  val superAdd                       = (x: Int) => (y: Int) => x + y // currying
  val superAddOne                    = (x: Int) => x * x
  val superAddTwo: (Int, Int) => Int = (x: Int, y: Int) => x + y
  println("super add superAdd(10)(20)")

  superAdd(10)(20)

}
