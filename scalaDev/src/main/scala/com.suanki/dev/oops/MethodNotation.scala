package com.suanki.tutorials.oops

import scala.language.postfixOps

object MethodNotation extends App {

  class Person(val name: String, favouriteMovie: String) {

    def likes(movie: String): Boolean = movie == favouriteMovie

    def hangOutWith(person: Person): String =
      s"${person.name} is hanging out with ${this.name}"

    def +(person: Person): String =
      s"${person.name} is hanging out with ${this.name}"

//put space after bang
    def unary_! : String = s"${name} wow!"

    def isAlive: String = s"${name} is alive"

    def apply(): String =
      s"Hi , my name is $name and favourite movie is $favouriteMovie"
  }

  val mary = new Person("Mary", "inceptino")

  println(mary.likes("inception"))

  // infix notation = operator notation (only work with single parameter
  println(mary likes "inception")

  // "operator' in scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)

  // All operators are method
  println(1 + 2)
  println(1.+(2))

  // +++++++++++++++PREFIX NOTATION++++++++++++++++++++++
  val x = -1 // uniray operator is also method

  val y = 1.unary_- // equalante to x = -1

  // uniray_ prefix only work with -, +, ~, ~

  println(!mary)
  println(mary.unary_!)

  // ++++++++++++++POSTFIX +++++++++++++++++++++++
  // only available to method withour parameter

  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equilant

}
