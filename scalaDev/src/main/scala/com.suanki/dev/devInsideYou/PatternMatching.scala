package com.suanki.tutorials.devInsideYou

object PatternMatching {

  def main(args: Array[String]): Unit = {
    println("=" * 50)
    code(args)
    println("=" * 50)
  }

  def code(args: Array[String]): Unit = {
    object top {
      object sub {
        val one = 1
      }
    }

    // class person

    class Person(name: String, age: Int) {
      def isAdult: Boolean = age >= 18
    }

    case class Animal(animalType: String, isDomestic: Boolean, maxAge: Int)

    lazy val one =
      1 // this can't be def, var,  required stable constant value
    def method(input: Any): Any = input match {
      case animal @ Animal(animalType: String, isDomestic @ true, maxAge @ 20) =>
        s"animal is domestic $maxAge"
      case animal @ Animal(animalType: String, isDomestic @ true, maxAge @ 30) =>
        s"animal is domestic $maxAge"
      case p @ Animal(a, b, c) =>
        s"""Mathced $p
           |this is toString implementation
           |${p.toString}
           |""".stripMargin
      case (first @ ("pitbull", true, 10), second @ ("pitbull", true, 11)) =>
        s"recieved $first $second"
      case Tuple2(first, second) => s"recieved $first $second"
      case person: Person if person.isAdult =>
        s"putting a guard ${person.isAdult}"
      case person: Person => person.isAdult
      case null           => null
      case `one`          => s"this is one"
      case top.sub.one    => s"this is ${top.sub.one}"
      case name @ "test" =>
        s"this is bounded variable $name :( name can be used here !"
      case n: Boolean => s"this also work boolean: $n"
      case myVar      => myVar
    }

    def show(input: Any): Unit = println(method(input))

    show("Sujeet")
    show(2)
    show(true)
    show('c')
    show(null)
    show(1)
    show(top.sub.one)
    show("test")

    val alice = new Person(
      name = "Alice",
      age = 27
    )

    show(alice)

    // case class
    val dog1 = Animal("K9", true, 20)
    val dog2 = Animal("K9", true, 30)

    show(dog1)
    show(dog2)

    val tuple = Tuple2(dog1, dog2)
    show(tuple)

    // set
    // no duplicates, no insertion order

    // seq
    // duplication allowed, insertion order preserve

    println(Set(1, 2, 3, 4, 5))
    println(Set(1, 2, 3, 4, 5, 3, 32, 1, 42, 11))
    println(Set(2, 2, 2, 1, 1, 4))

    println(Set(1, 2, 4) == Set(1, 2, 4, 2)) // true

    println("creation of set")

    val mySet: Set[Int] = Set.empty[Int]
    println(mySet)

    println("-" * 50)

    def method1(input: Any): String = input match {
      case Seq()            => "empty sequence"
      case Seq(last)        => s"se1 of last ${Seq(last)}"
      case Seq(first, last) => s"se1 of last ${Seq(first)} ${Seq(last)}"
      case n @ Seq(1, 2, 3) => s"$n"
      case n @ Seq(first, _*) =>
        s"all the other element with at least one element $n"
      case _ => s"default"
    }

    def show1(input: Any): Unit = println(method1(input))

    show1(Seq.empty[Int])
    show1(Seq(1))
    show1(Seq(1, 2))
    show1(Seq(1, 2, 3))
    show1(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9))

  }
}
