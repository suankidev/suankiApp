package com.suanki.dev.typeSystem.genericType

case class FoodBowl[F <: Food](contents: F) {

  override def toString: String = s"A yummy bowl of ${contents.name}"
}

class UpperBound {

  def code: Unit = {

    val appleBowl = FoodBowl(Apple("fuji"))
    // val dogBowl = FoodBowl(Dog("K9")) //wont' run since this is not type of Food,
    // bound not satisfied for dog
  }
}
