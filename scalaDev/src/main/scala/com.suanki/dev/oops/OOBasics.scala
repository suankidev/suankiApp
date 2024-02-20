package com.suanki.dev.oops

object OOBasics extends App {

  val person = new Person("sujeet", 95)

  println(person)
  println(person.age) // won;t work unless you make it member
  println(person.x)
  person.greet("Kamesh")
  println()
  person.greet()

  val person_one = new Person("Ramesh")
  val person_two = new Person()
}

class Person(name: String, val age: Int) { // constructor
  // class parameter is not field not accessible iwth persone.name
  // to make it use val

  // body 34
  val x = 2

  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} say hi ${name}")

  // overloading
  def greet(): Unit = println(s"Hello ${name}")
  // here name refers to classes parameters

  // overloading of constructor
  def this(name: String) = this(name, 0)
  def this() = this("Default")

}
