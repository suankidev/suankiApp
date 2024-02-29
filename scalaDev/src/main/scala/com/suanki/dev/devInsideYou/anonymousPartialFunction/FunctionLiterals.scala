package com.suanki.dev.devInsideYou.anonymousPartialFunction

object FunctionLiterals {

  def code(args: Array[String]): Unit = {

    val totalFunction: Int => String =
      argument => s"""\"${argument}\""""

    val randomNumber = scala.util.Random.nextInt()

//    println(" " + randomNumber)
//    println(totalFunction(randomNumber))

    // chatty
    trait Chatty {
      def sayHi(greeting: String): Unit
    }

    class Person(name: String) extends Chatty {

      override def sayHi(greeting: String): Unit = {
        println(s"HI i'm $name, $greeting")
      }
    }

    val alice: Chatty = new Person("Alice")
    val bob: Chatty   = new Person("Bob")

    alice.sayHi("hello")

    // so, what the point from above,
    // we don't need to create person, we can create anonymous class directly

    val singleton: Chatty = new Chatty {
      override def sayHi(greeting: String): Unit = {
        println(s"HI i'm,  $greeting")
      }
    }

    singleton.sayHi("this is singleton greeting!")

    // this is what happening with function literals
    // renaming Chatty with FunctionOne

    trait SimpleFunctionOne {
      def apply(greeting: String): Unit
    }

    class Body(name: String) extends SimpleFunctionOne {

      override def apply(greeting: String): Unit = {
        println(s"HI i'm $name, $greeting")
      }
    }

    val bodyOne: SimpleFunctionOne = new Body("siya")
    bodyOne("power full apply method!")

    // it should be single abstract method
    trait SimpleFunctionTwo[-input, +output] {
      def apply(input: Int): output
    }

    val totalFunctionsWithoutSyntaticSugar: SimpleFunctionTwo[Int, String] = new SimpleFunctionTwo[Int, String] {
      override def apply(num: Int): String = s"""\"${num}\""""
    }

    val totalFunctionsWithoutSyntaticSugarOne: Function1[Int, String] = new Function1[Int, String] {
      override def apply(num: Int): String = s"""\"${num}\""""
    }

    println()

    println(
      totalFunctionsWithoutSyntaticSugar(randomNumber)
    )

    println(
      totalFunction(randomNumber)
    )

  }
}
