package com.suanki.dev.oops

object AbstractInScala extends App {

  // abstract field and class and method
  /** CLASS: Abstract class can't be instantiated
    *
    * Abstract vs Trait
    *
    * both can have abstract and non-abstract
    *   1. traits do not have constructor parameters 2. multiple traits may be inherited by the same class 3. traits =
    *      behaviour , abstract class => "thing"
    */
  abstract class Animal {
    val creatureType: String

    def eat: String

  }

  class Dog extends Animal {
    override val creatureType: String = "domestic"

    override def eat: String =
      s"""Animal is of type $creatureType
      and eat like Crunch Crunch"""

  }

  val k9 = new Dog
  k9.eat

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {

    override val creatureType: String = "carnivorous"

    override def eat: String = s"crunch crunch"

    override def eat(animal: Animal): Unit = println(
      s"""I am ${creatureType} and eating ${animal.creatureType}
         |""".stripMargin
    )

  }

  val crocodile = new Crocodile()
  crocodile.eat(k9)

}
