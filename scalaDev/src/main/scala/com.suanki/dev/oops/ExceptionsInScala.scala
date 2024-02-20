package com.suanki.tutorials.oops

object ExceptionsInScala extends App {

  val x: String = null

  // lazy val lengthOfString = x.length

  // println(lengthOfString)  //null pointer exception
//
// val aWeiredValue :String = throw new NullPointerException

  // throwable classes extends Throwable Class
  // Exception and Error are major Throwable subtypes

  // how to catch exception
  def get(exception: Boolean) =
    if (exception) throw new RuntimeException("number is not correct!")
    else 42

  try {
    get(true)
  } catch {
    case e: NullPointerException =>
      println("Nullpointer exception is thrown")
    case e: RuntimeException => println("Runtime exception is thrown")
  } finally {
    // code that will get executed no matter what
    println("End of program")
  }

  // 3. define your own exception
  class MyException extends Exception

  val num = 9
  try {
    if (num > 5) throw new MyException
  } catch {
    case e: MyException => println("Custom exception is thrown")
  }

  /**   1. crash your program with OutOfMemory Error 2. crash with stackOverflowError 3. PocketCalculator --add(x,y)
    *      --subtract(x,y) --multiply(x,y) --divide(x,y)
    *
    * Throw --overflowWxception when add excede Int.MAX_VALUE --underlowExceptionif sub excede Int.MIN_VALUE
    * -MAtchCaluationException or division by 0
    */

}
