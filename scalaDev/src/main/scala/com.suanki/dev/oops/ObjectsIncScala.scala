package com.suanki.tutorials.oops

object ObjectsIncScala {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY("STATIC")
  object Person { // type + its only instance
    // "static/"class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    //    def from(mother: Person,father: Person): Person = new Person("Bobbie")
    def apply(mother: Person, father: Person): Person = new Person(
      "Bobbie"
    )
  }

  class Person(val name: String) {
    // instance level property

  } // COMPANION

  def main(args: Array[String]): Unit = {
    println("+" * 50)

    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE
    val mary = new Person("mary")
    val john = new Person("John")

    println(mary == john)

    val person1 = Person
    val person2 = Person
    println(person1 == person2)

    //  val bobbie = Person.apply(mary,john)
    val bobbie = Person(mary, john)

    // Scala Application =  scala object with
    // def main(args: Array[String]):Unit

    print("+" * 50)

  }

}
