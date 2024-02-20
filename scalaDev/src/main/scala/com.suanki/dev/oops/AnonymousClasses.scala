package com.suanki.tutorials.oops

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("Ahahahahah")
  }

  println(funnyAnimal.getClass)

}
