package com.suanki.dev.devInsideYou.patternMatching

object Main extends App {

  println("=" * 50)

  /** pattern matching is not made for objects
    */

  // Variable pattern
  def show(x: Any): Unit = {
//    println(Exercise.VariablePattern(x))
  }

  show(1)
  show("one")
  show(true)
  show(false)
  show("hi")
  show('c')
  show(null)
  show(Top.Sub.One)

  // non permitive matching
  def nonPrimitivePatternShow(x: Any): Unit = {
//    println(Exercise.nonPrimitivePattern(x))
  }

  val alice = new Person(name = "Alice", age = 20)
  val dogK9 = new Dog(name = "k9", age = 2)

  nonPrimitivePatternShow(alice)
  nonPrimitivePatternShow(dogK9)

  // every tuple is case class , we can use it in pattern match
  val shyam     = Student("Shyam", 15)
  val ram       = Student("Ram", 19)
  val nametuple = Tuple2(shyam, ram)

  nonPrimitivePatternShow(shyam)
  nonPrimitivePatternShow(ram)

  nonPrimitivePatternShow(nametuple)

  // pattern match on collection
  def patternMatchOnCollectionShow(x: Any): Unit = {
    println(Exercise.patternMatchOnCollection(x))
  }

  val x = Set(1, 2, 3)
  patternMatchOnCollectionShow(x)
  patternMatchOnCollectionShow(Seq(1, 2, 4))
  patternMatchOnCollectionShow(Seq())
  patternMatchOnCollectionShow(Seq(1, 2, 4, 5))
  patternMatchOnCollectionShow(Exercise.MyFavouriteString(Seq("Ram", "Shyam")))

  println("=" * 50)
}
