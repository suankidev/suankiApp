package com.suanki.dev.oops

object CaseClasses extends App {

  /*
  equals, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // class parameters are fields
  val jim = new Person("jim", 34)
  println(jim.name)

  // 2. sensible toString
  println(jim.toString)
  println(jim)

  // equals and hashCode implemented out of the box
  val jim2 = new Person("jim", 34)
  println(jim == jim2)

  // case classes have handy copy method
  val jim3 = jim.copy()
  val jim4 = jim.copy(age = 45)

  // case have companion objects
  val thePerson = Person
  val mary      = Person("marry", 23)

  // 6. CCS are serializable
  // can be used in pattern matching

  case object UnitedKingdom {
    // they have same propery as cc except they don't have companion object
    def name: String = "The uk of GB and NI"
  }

}
