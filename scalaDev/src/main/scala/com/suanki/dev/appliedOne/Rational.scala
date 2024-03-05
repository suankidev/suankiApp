package com.suanki.dev.appliedOne
import com.suanki.dev.appliedOne.Remote.{demo, test}

import language.implicitConversions

class Rational(val n: Int, val d: Int) {
  private[this] val test: Int = 20 // this can't be access from other.test
  private val demo: Int       = 20 // this can be access from other.test
  // adding precondition
  require(d != 0, s"d != 0")
  override def toString: String = s"""R($n/$d)"""

  def this(i: Int) = this(1, i)

  def min1(other: Rational): Rational = {
    if ((this.n.toDouble / this.d) < (other.n.toDouble / other.d))
      this
    else
      other
  }

  def +(other: Rational): Rational = {
    new Rational(
      (this.n * other.d) + (this.d * other.d),
      this.d * other.d
    )
  }

  def +(i: Int): Rational = {
    this + Rational(i)
  }
}

//companion object
object Rational {

  def apply(n: Int, d: Int): Rational  = new Rational(n, d)
  implicit def apply(d: Int): Rational = new Rational(1, d)
}

trait Voice {

  val noOfKey: Int = -1
}

class Remote private (a: Int) extends Voice {
  def getVal: String = a + test + " " + demo
}

object Remote {

  val test: String                        = "test"
  private val demo: String                = "demo"
  private[this] val notAccessible: String = "notAccessible"

  def apply(): Remote = new Remote(10)

}

object RationalMain extends App {
  println("=" * 40)

  // A Rational Class
  val full  = new Rational(2, 1)
  val fifth = new Rational(2, 5)
  //  val rational1 = new Rational(2, 0) // should prevent this
  println(full)
  // referencing self, infix notation
  println(full + fifth)
  println(full + new Rational(1)) // auxiliary constructor

  // adding an rational to int
  println(full + 9)

  // adding an int to Rational--> use implicit  on apply method
  println(5 + full) // rewrite->
  // look for int and rational

  println("=" * 40)

}
