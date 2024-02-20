package com.suanki.tutorials.basics

import com.suanki.tutorials.Exercise.show

object FiveSubroutinesPartOne extends App {

  show()

  println(args.mkString(","))

  println(" passing parameter to a method")
  FarAway.code(true, false)
  FarAway.code(true, true)

  // we can tell compiler what we want
  val c: (Boolean, Boolean) => Unit = FarAway.code3

  println(s"What we want: ${c}")

  //
  println(" we can bound this to val give it to client")
  val clientKnowThis = FarAway.code

  clientKnowThis(true, true)

  println("we can make val code3")
  FarAway.code3(true, true)

  show()
}

object FarAway {

  val code3 = {
    println("I'm about to create subroutine")
    (isSunnyDay: Boolean, isRainyDay: Boolean) =>
      def result = {
        if (isSunnyDay)
          "I'm Happy"
        else if (isRainyDay)
          "i'm said because it's raining"
        else
          "I'm Sad "

      }

      println(result)
  }: (Boolean, Boolean) => Unit

  def a = 1337 // literal for a number

  def b = true // literal for boolean

  def c = 't'

  def d = "true"

  def e = {}

  def f = (first: Boolean, second: Boolean) => {} // literal for function

  def code2 = (isSunnyDay: Boolean, isRainyDay: Boolean) => code1(isRainyDay, isSunnyDay)

  // second code
  def code1: (Boolean, Boolean) => String = { (isSunnyDay: Boolean, isRainyDay: Boolean) =>
    if (isRainyDay)
      "test"
    else if (isSunnyDay)
      "test fail"
    else
      "non matched"

  }

  // first code
  def code = {
    println("I'm about to create subroutine")
    (isSunnyDay: Boolean, isRainyDay: Boolean) =>
      def result = {
        if (isSunnyDay)
          "I'm Happy"
        else if (isRainyDay)
          "i'm said because it's raining"
        else
          "I'm Sad "

      }

      println(result)
  }: (Boolean, Boolean) => Unit

}
