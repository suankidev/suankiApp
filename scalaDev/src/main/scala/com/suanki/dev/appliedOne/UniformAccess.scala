package com.suanki.dev.appliedOne

abstract class Food {
  val name: String
}

abstract class Fruit extends Food

case class Banana(name: String) extends Fruit
case class Apple(name: String)  extends Fruit
case class Orange(name: String) extends Fruit

abstract class Cereal            extends Food
case class Granola(name: String) extends Cereal
case class Muesli(name: String)  extends Cereal

case class Bowl(food: Food) {
  override def toString: String = s"A bowl of yummy ${food.name}"
  def contents                  = food
}

//now it doesn't know of name
case class BowlOne[F](food: F) {
  override def toString: String = s"A bowl of yummy ${food}"
  def contents                  = food
}

//upper bound
case class FoodBowl[+F <: Food](f: F) {
  override def toString: String = s"A bowl of yummy ${f.name}"
  def contents                  = f
  def mix[M >: F <: Food](other: M): MixedFoodBowl[F] = {
    MixedFoodBowl(f, other)
  }
}

case class MixedFoodBowl[+F <: Food](f1: Food, f2: Food) {

  override def toString: String = s"${f1.name} mixed with ${f2.name}"
}

trait OtherMethods {

  def serveToFruitEater(foodBowl: FoodBowl[Fruit]): Unit = {
    println(s"mmm, those ${foodBowl.contents.name} is yummy")
  }

  def serveToFoodEater(foodBowl: FoodBowl[Food]): Unit = {
    println(s"mmm, those ${foodBowl.contents.name} is yummy")
  }

  def describeAnApple(fd: Apple => Description): Description = {
    fd(Apple("fuji"))
  }
  def describeAFruit(fd: Fruit => Description): Description = {
    fd(Apple("fuji"))
  }

}

trait Description {
  val describe: String

}

case class Taste(describe: String)   extends Description
case class Texture(describe: String) extends Description

object UniformAccess extends App with PrintDash with OtherMethods {
  start

  val fuji  = Apple("Fuji")
  val alpen = Muesli("Alpen")

  val FoodBowlOfApple  = FoodBowl(fuji)
  val FoodBowlOfCereal = FoodBowl(alpen)

//  println(
//    FoodBowl(FoodBowlOfApple).mix(FoodBowlOfCereal)
//  )
  end
}
