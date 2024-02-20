package com.suanki.tutorials.Exercise

import scala.language.postfixOps

object MethodNotation extends App {

  class Person(name: String, favouriteMovie: String, val num: Int = 0) {

    def +(nickname: String): Person =
      new Person(s"my name is $name ($nickname) i ", favouriteMovie)

    def unary_+ : Person = new Person(name, favouriteMovie, num + 1)

    def learns(thing: String): String = s"$name is learning $thing"

    def learnScala: String = learns("Scala")

    def apply(n: Int): String =
      s"$name watch $favouriteMovie movie $n times"
    def apply(): String = s"$name watch $favouriteMovie "

  }

  val mary = new Person("Mary", "Inception")

  println((mary + "The Rockstor")())
  println((+mary).num)

  println(mary learnScala)
  println(mary(10))

}
