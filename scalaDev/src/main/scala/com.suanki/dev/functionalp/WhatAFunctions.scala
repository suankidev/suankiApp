package com.suanki.tutorials.functionalp

object WhatAFunctions extends App {
  // DREAM:  use function as first class elemnts
  // problem: oop

  println("Function in Scala==>" + "+" * 50)
  val square: Int => Int = x => x * x
  var x                  = 10
  println(s"Square of ${x} is ${square(x)}")

  def test: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 * v2 * 80
  }

  def superAdderOne: Function1[Int, Function1[Int, Int]] =
    new Function[Int, Function1[Int, Int]] {
      override def apply(x: Int): Int => Int = new Function[Int, Int] {
        override def apply(y: Int): Int = x + y
      }
    }

  def test1(x: Int)       = x + 10
  def test2: (Int) => Int = x => x * x
  def test3: () => Int    = () => 42
  val p                   = test1(10)
  val q                   = test2
  val r                   = test3

  println(s"${test1(10)} ${p}")
  println(s"test2 ${test2(10)} ${q(10)}")
  println(s"test3 ${test3}")

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // function type == Function1..Function2, ...
  val stringToIntConverter = new Function1[Int, String] {
    override def apply(v1: Int): String = v1.toString
  }

  println(stringToIntConverter(3) + 4) // 7

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // ALL SCALA FUNCTIONS ARE OBJECTS

  // A FUNCTION WHICH TAKES 2 STRINGS AND CONCATENATES THEM
  def concatinate: (String, String) => String =
    new Function2[String, String, String] {
      override def apply(v1: String, v2: String): String = s"${v1}$v2"
    }

  println(concatinate("hello", "scala"))

  def superadder: Function1[Int, Function1[Int, Int]] =
    new Function[Int, Function1[Int, Int]] {
      override def apply(x: Int): Int => Int = new Function[Int, Int] {
        override def apply(y: Int): Int = x + y
      }
    }

  val adder3 = superadder(4)
  println(adder3(3))
  println(superadder(3)(4))

  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  println("Function in Scala==>" + "+" * 50)
}
