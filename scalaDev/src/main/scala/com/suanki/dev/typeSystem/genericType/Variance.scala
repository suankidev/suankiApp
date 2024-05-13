package com.suanki.dev.typeSystem.genericType
import com.suanki.logger.StdOut._

//co-variance by putting +
case class FoodBowl1[+F <: Food](contents: F) {

  override def toString: String = s"A yummy bowl of ${contents.name}"
}

/**   1. what we want is for FoodBowl[Apple] to be subtype of FoodBowl[Fruit] and
  *      FoodBowl[Food] 2. This relationship is known as varaince and is demarked by +
  *      and- on the generic 3. [+F]mean theat the type parameter is co-variant 4. [-F]
  *      mean that the type parameter is contra-variant 5. if if a type parameter has
  *      neither +or - , it is considered invariant
  */

class Variance {

  // only allow Bowl[Fruit] to be given to Fruit eater
  def serveToFruitEater(fruitBowl: FoodBowl1[Fruit]) =
    s"mmm those ${fruitBowl.contents.name}s very yummy"

  def serveToFruitEaterOne(fruitBowl: FoodBowl1[Food]) =
    s"mmm those ${fruitBowl.contents.name}s very yummy"

  def code: Unit = {

    val fuji   = Apple("fuji")
    val cereal = Muesli("Muesli kind")

    val appleBowl = FoodBowl1[Fruit](fuji)
    serveToFruitEater(appleBowl).show
    serveToFruitEaterOne(appleBowl).show

    // so expected typeis FoodBowl1[Fruit]
    /** Note: com.suanki.dev.typeSystem.genericType.Apple <:
      * com.suanki.dev.typeSystem.genericType.Food, but class FoodBowl1 is invariant in
      * type F. [error] You may wish to define F as +F instead. (SLS 4.5)
      */
    // 1.
    val cerealBowl = FoodBowl1[Muesli](cereal)
//    serveToFruitEater(cerealBowl)  // FoodBowl1[Museli]
    serveToFruitEaterOne(cerealBowl) // FoodBowl1[Museli]
    // FoodBowl1[Museli]

    // 2.
    val appleBowl1 = FoodBowl1[Apple](fuji) // FoodBowl1[Apple]
//   serveToFruitEater(appleBowl1)
    serveToFruitEaterOne(appleBowl1)

    // 3. but, this wont' work, b/c FoodBowl1[Muesli] is not covarient for FoodBowl1[Fruit]
//    serveToFruitEater(cerealBowl)

    // but then FoodBowl[Muesli] is covarient of FoodBowl1[Food]
    serveToFruitEaterOne(cerealBowl)
  }

}
