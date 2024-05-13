package com.suanki.dev.typeSystem.genericType
import com.suanki.logger.StdOut._

trait Description {
  val describe: String
}

case class Taste(describe: String) extends Description

case class Texture(describe: String) extends Description

class FunctionVariance {

  def describeAnApple(fn: Apple => Description) = fn(Apple("fuji"))
  def describeAFruit(fn: Fruit => Description)  = fn(Apple("fuji"))

  def bummpyOrange(fn: Orange => Texture) = fn(Orange("kinoo"))

  def juicyFruit: Fruit => Taste =
    fruit => Taste(s"This ${fruit.name} is nice and juicy")

  lazy val functionVariance: Unit = {

    describeAnApple(
      juicyFruit
    ).show // contravariant at input but co-variant at o/p so this work

    describeAFruit(juicyFruit)

//    bummpyOrange(juicyFruit)//wont' work

  }

}
