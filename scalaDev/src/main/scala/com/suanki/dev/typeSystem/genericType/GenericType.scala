package com.suanki.dev.typeSystem.genericType

case class Bowl[F](food: F) {
  //  override def toString: String = s"A Bowl of yummy ${food.name} of type."
  lazy val contents: Unit = println(
    s"bowl content ${food} and location " // food.name is not available
  )                                       // Apple(Fuji Apple)
}

class GenericType {

  def code: Unit = {

    val fuji  = Apple("Fuji Apple")
    val alpen = Apple("Alpen Apple")

    val fruitBowl = Bowl(fuji)
    //  val fakeBowl  = Bowl(2)  //fail it's not type of Food

    fruitBowl.contents

    // but there is problem, see it also accept dog ,which should not, see upperBound
    val dog     = Dog("k9")
    val dogBowl = Bowl(dog)
    dogBowl.contents
  }
}
