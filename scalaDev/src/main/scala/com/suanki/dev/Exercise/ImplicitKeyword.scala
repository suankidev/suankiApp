package com.suanki.dev.Exercise

import scala.language.implicitConversions

/** In scala implicit works as:
  *
  * \1. Converter: Implicitly type conversion : It converts the error producing assignment into intended type String is
  * not the sub type of Int , so error happens in line 2. To resolve the error the compiler will look for such a method
  * in the scope which has implicit keyword and takes a String as argument and returns an Int .
  *
  * 2. Parameter value injector: Implicitly receiver conversion: We generally by receiver call object's properties, eg.
  * methods or variables . So to call any property by a receiver the property must be the member of that receiver's
  * class/object.
  *
  * 3. Extension method:
  */
object ImplicitKeyword extends App {

  // https://stackoverflow.com/questions/10375633/understanding-implicit-in-scala

  // 1. As converter
  implicit def intToString(d: String): Int = 5

  val x: String = ""

  println(x)

  val y: Int = x

  println(y)

  // 2. implicitly receiver conversion

  class Mahadi {

    val haveCar: String = "BMW"

  }

  class Johnny {

    val haveTv: String = "Sony"

  }

  val mahadi = new Mahadi

  // mahadi.haveTv
  /*
  Here mahadi.haveTv will produce an error. Because scala compiler will first look for the haveTv property to
   mahadi receiver. It will not find. Second it will look for a method in scope
   having implicit keyword which take Mahadi object as argument and returns Johnny object.
   But it does not have here. So it will create error. But the following is okay.
   */

  // but after this
  implicit def z(a: Mahadi): Johnny = new Johnny

  mahadi.haveTv // compiler will use z here like new Johnny().haveTv

  println(mahadi.haveTv) // result Sony & no error

  {
    /*3. Implicitly parameter injection
  Implicitly parameter injection: If we call a method and do not pass its parameter value,
  it will cause an error. The scala compiler works like this - first
  will try to pass value, but it will get no direct value for the parameter.
     */
    implicit val z: Int = 10

    def hello(a: Int): Int = a

    // x  //Error
    /*
  If we call a method and do not pass its parameter value, it will cause an error. The scala compiler works
   like this - first will try to pass value, but it will get no direct value for the parameter.
Second if the parameter has any implicit keyword it will look for any val in the scope
which have the same type of value. If not get it will cause error.
     */

    def x(implicit a: Int) = a

    // x // compiler will use implicit like this x(z)
    println(x) // will result 10 & no error.

  }

  {

    // to do this we need to create an implicit class within a object/class/trait .
    object Extensions {

      implicit class MeterToCm(meter: Int) {

        def meterToCm = {
          meter * 100
        }

      }

    }

    import Extensions._
    println(2.meterToCm) // result 200

  }

  {

    object TestTrait {
      implicit class Test(age: Int) {
        def showAge: Unit = println(age)
      }
    }
    import TestTrait._
    28.showAge
  }

  {
    implicit val y: Int = 20

    def hello(x: Int)(implicit y: Int): Unit = {
      println(x + y)
    }

    hello(10)
  }

}

object Demo5 {
  implicit class Test(val x: Int) extends AnyVal {
    def timesDo(fn: => Unit): Unit = {
      for (x <- 1 to x) fn
    }
  }

  5 timesDo println("hello")
}
