package com.suanki.dev.collections

class VectorInScala {

  def code: Unit = {
    println("Vector")
    val nums = Vector(1, 2, 3, 4, 5)

    val strings = Vector("one", "two")

    case class Person(name: String)
    val people = Vector(
      Person("Bert"),
      Person("Ernie"),
      Person("Grover")
    )

    val a = Vector(1, 2, 3)   // Vector(1, 2, 3)
    val b = a :+ 4            // Vector(1, 2, 3, 4)
    val c = a ++ Vector(4, 5) // Vector(1, 2, 3, 4, 5)
    val d = 4 +: a
    println(s"value of a $a")
    println(s"value of b $b")
    println(s"value of c $c")
    println(s"value of d $d")

    // prepend
    val e = Vector(1, 2, 3)    // Vector(1, 2, 3)
    val f = 0 +: e             // Vector(0, 1, 2, 3)
    val g = Vector(-1, 0) ++ e // Vector(-1, 0, 1, 2, 3) //or ++:
    println(s"value of e $e")
    println(s"value of f $f")
    println(s"value of g $g")
  }
}

class ListInScala {

  def code: Unit = {
    println("List")
    println("=" * 40)
    val ints    = List(1, 2, 3)
    val names   = List("Joel", "Chris", "Ed")
    val numList = List.apply(23, 45, 67, 32, 45)
    val d       = 1 :: 2 :: Nil

    println(s"value of d ${d}")
    // by default it creates mutable list

    // adding elements to a Lis

    val a = List(1, 23, 3)
    val b = a :: a // prepending a to the a
    println(s"value of a ${a}")
    println(s"value of b ${b}")

    val c = d ++ a // or :::
    println(s"value of c  ${c}")

    // prepending
    println(s"prepending 4 ${4 +: c}")
    println(s"appending 4 ${c :+ 4}")
    println(s"adding an element to list ${0 :: a}")

    // loop over lists
    for (name <- names) println(s"iterating: $name")

    //  for name <- names do println(name) // work in scala-3

  }

  def hof: Unit = {
    val mainList   = List(3, 2, 1)
    val numberList = List(23, 232, 1, 2, 76, 45, 23)
    val with4      = 4 :: mainList  // re-uses mainList, costs one :: instance
    val with42     = 42 :: mainList // also re-uses mainList, cost one :: instance

    println(with42)

    println(mainList :: 42 :: Nil)

    println(s"arity: ${mainList.productArity}")
    println(s"nth element ${mainList.productElement(0)}")

    println(s"select a elemnet ${mainList(1)}")

    println(
      mainList.sortWith((x, y) => x < y),
      mainList.count(x => x == 2),
      mainList.zipWithIndex.toMap
    )

  }
}
object ListInScala extends App {
  println("=" * 40)

  val listInScala = new ListInScala()
//  listInScala.code
  listInScala.hof
  // Vector
  println("=" * 40)

  val vectorInScala = new VectorInScala()
//  vectorInScala.code

  println("=" * 40)
}
