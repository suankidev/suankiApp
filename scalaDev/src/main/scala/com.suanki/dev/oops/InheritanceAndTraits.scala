package com.suanki.tutorials.oops

object InheritanceAndTraits extends App {

  sealed class Animal {
    val creatureType = "wild"
    final val classType: String =
      "Animal" // can't ve overwritten in extended class or in the same class

    def eat = println("noncom")
    private def nomail(): Unit = println(
      "not accessible outside this class directly"
    )
    protected def noOfLegs: String = s"no of legs is 3"
  }

  class Cat extends Animal {

    def crunch: Unit = {
      noOfLegs
    }
  }

  // Constructors
  class Person(name: String, age: Int) {

    def this(name: String) = this(name, 0)
    //    def this() = this(name)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

//Override

  final class Dog(dogType: String) extends Animal {
    override val creatureType: String = "domestic"
    override def eat = {
      super.eat
      println("Crunch Crunch ..!")
    }
  }

  val cat = new Cat
  cat.eat // only eat is accesible here!
  val k9 = new Dog("k9")
  k9.eat
  cat.eat

  // type substitution (broad Polymorphism)
  val unknownAnimal: Animal = new Dog("K10")
  unknownAnimal.eat

  // overriding vs overloading
  // super
  // preventing overrides
  // use final
  // class can be final //final class Person
  // seal the class: can extend the classes in this file, prevent extension other files
  // sealed class Animal

}
