package com.suanki.dev.devInsideYou.patternMatching

object Top {
  object Sub {
    val One = 1
  }
}

class Person(name: String, age: Int) {
  def isAdult: Boolean = age >= 18
  def getName: String  = name
}

case class Dog(name: String, age: Int)

case class Student(name: String, age: Int)

object Exercise {

  def VariablePattern(input: Boolean): String = input match {
    case name @ (n: Boolean) /*name @ (_: Boolean)*/ => s"matched a boolean "
  }

  /** Variable and constant pattern matching
    *
    * @param input
    * @return
    */
  def VariablePattern(input: Any): Any = {
    // should alwys starts with Capital letter  if wants to use it
    // only exception is null see below
    val One = 1
    input match {
      case name @ (n: Boolean) /*name @ (_: Boolean)*/ => s"matched a boolean  and ${n} "
      case name @ Main                                 => s"matchd main object ${name}"
      case name @ true                                 => s"Boolean value $name" // bind the value to a name
//      case _: Boolean /*name @ (_: Boolean)*/          => s"matched a boolean in method2" // Boolean is matched
      case Top.Sub.One        => Top.Sub.One            // object matching
      case Top                => Top
      case null               => "null"
      case 2                  => 2                      // constant pattern
      case One                => s"this is $input"      // matching literal
      case _: Int | _: String => s"""isthis is int, isthis is string pattern match on two thing at same time"""
      case variable           => s"inside 1: $variable" // take anything like default case

    }
  }

  def nonPrimitivePattern(input: Any): Any = {

    input match {
      case p @ Dog(n, a @ 3)                 => s"""Matched ${p.getClass.getSimpleName} $n who is $a years old"""
      case p @ Dog(n, a)                     => s"""Matched ${p.getClass.getSimpleName} $n who is $a years old"""
      case person: Person if person.isAdult  => s"Guard: ${person.getName}"
      case student @ Student(n, a) if a > 18 => s"""student: name: ${n}, adult age: ${a}"""
      case student @ Student(n, a) if a < 18 => s"""student: name: ${n}, adult age: ${a}"""
      case person: Person                    => person.isAdult
      case (first @ Student(n, a @ 15), second: Student) /*tuple pattern*/ =>
        s"""received first: ${first} second:${second} """
      case Tuple2(first: Student, second: Student) /*tuple pattern*/ =>
        s"""received first: ${first} second:${second} """

    }
  }

  final case class MyFavouriteString(name: Seq[String])

  def patternMatchOnCollection(input: Any): Any = {

    input match {
      case Seq(a, b, c)                           => s""" $a Sequence"""
      case Seq()                                  => s"""Empty Sequence"""
      case Seq(first, second, _*)                 => s"""at lease 2 element"""
      case Seq(first, second, third, others @ _*) => s"""at lease 3 element"""
      case Seq(first, _*)                         => s"""at lease 1 element"""
      case wrapper: MyFavouriteString             => s"""wrapper: ${wrapper.name}"""
      case _                                      => s"""default case"""

    }
  }

  def code2(input: Any): Any = (input: @unchecked) match {
    case _ => "removed warning by using uncheked above"
  }

}
