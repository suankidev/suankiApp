package com.suanki.dev.typeSystem
package object genericType {
  abstract class Food {
    val name: String
  }

  abstract class Fruit            extends Food
  case class Banana(name: String) extends Fruit
  case class Apple(name: String)  extends Fruit
  case class Orange(name: String) extends Fruit

  abstract class Cereal            extends Food
  case class Granola(name: String) extends Food
  case class Muesli(name: String)  extends Food

  abstract class Animal {
    val name: String

    override def toString: String = s"Animal - $name"
  }

  case class Dog(name: String) extends Animal

}
