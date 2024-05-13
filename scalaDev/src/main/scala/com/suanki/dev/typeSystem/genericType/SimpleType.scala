package com.suanki.dev.typeSystem.genericType

case class SimpleBowl(food: Food) {

  override def toString: String = s"A Bowl of yummy ${food.name}"

  lazy val contents: Unit = println(
    s"bowl content $food and " // food.special can't be access since food is type of Food
  )
}

class SimpleType {

  def eat(f: Food): Unit = {

    println(s"eating ${f.name}, it's yummy")
  }

  lazy val simpleType = {

    val fuji         = Apple("Fuji Apple")
    val kindOfMuesli = Muesli("muesli ")

    eat(fuji)

    val fruitBowl   = SimpleBowl(fuji)
    val mueseliBowl = SimpleBowl(kindOfMuesli)

    fruitBowl.contents
    mueseliBowl.contents

  }

}
