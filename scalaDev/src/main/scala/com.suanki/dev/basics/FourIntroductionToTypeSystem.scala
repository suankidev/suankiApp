package com.suanki.tutorials.basics

import com.suanki.tutorials.Exercise.show

object FourIntroductionToTypeSystem extends App {

  show()
  // type for boolean
  type Conditional = Boolean
  // vals are immutable
  // compiler can infer types

  /** type inference
    */

  val x: Int            = 23
  val aString: String   = "hello"
  val aBoolean: Boolean = false
  val aChar: Char       = 'c'
  val aDouble: Double   = 3.1452
  val aShort: Short     = 6286 // 2byts
  val aLong: Long       = 416721832
  val aFloat: Float     = 2.0f
  val d: String         = "true": String
  val a: Int            = { 1337: Int }

//everything is in scope
  val b: Int              = { { 1337 }: Int }
  val isSiya: Conditional = true

  // Unit: is single unit of computation (scope)
  val e: Unit = {}

  // Return type is Any
  val f: Any  = if (aBoolean) 10 else "not 10"
  val f1: Any = { if (aBoolean) 10 else 11 }: Int
  val f2: Int = { if (aBoolean) 10 else 11 }: Int

  // println(f1 + 20) // type mismatch required string, found int(20)
  // destroying the type b/c f1 is type of any
  // but then sometime it works it's we who decide based on specific requirement
  // better to define the type to a specific type

  println(f2 + 20)

  val temp: Int = if (aBoolean) 10 else 11
  val f3: Int   = temp + 2

  val p: Boolean = {
    println("test Boolean!")
    p
  }

  // p  : p will call recursively

  /** type system is build on only two principle
    *
    *   1. Progress
    *      1. if an expression is well-typed (it compiles) 2. it either evaluates or is already a value
    *
    * 2. Preservation
    *   1. if an express is well typed and it evaluateds 2. the result has the same type
    */
  show()
}
