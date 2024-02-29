package com.suanki.dev.basics

object TWOExploringNewLearning extends App {

  /** val is val stored in memory def is a definition (only the computation is stored in memory lazy val (behaves teh
    * first time like a def and then like val var is variable (stored in memory like val, but can be changed
    *
    * Statement: produce side effects expression: evaluate to something(some type)
    */
  println("=" * 30)

  // flow of control; execute the line as soon as see this
  val line1 = { println("This print ln is in scope of ") }

  println(line1)
  println(line1)

  // lazy val : first time like a def then def
  lazy val lazyLine = { println("this is lazy line"); "54545" }

  println(lazyLine)
  println(lazyLine)

  // def
  def lazyValDef = {
    println("this alway gets executed when lazyValDef is called")
  }

  println(lazyValDef)

  println("=" * 30)

}
