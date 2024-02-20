package com.suanki.tutorials.devInsideYou.anonymousPartialFunction

object Exercises {

  def main(args: Array[String]) = {

    code(args)
  }

  def code(args: Array[String]): Unit = {

    trait SimpleFunctonOne[-input1, +output] {
      def apply(argument: input1): output

    }

    val myVal: (Int, Int, Int) => String = (v1, v2, v3) => s"$v1 $v2 $v3"
    val myVal1                           = (x: Int, y: Int, z: Int) => s"$x $y $z"

//     val totalFunction:Int => String = x => s"${Console.MAGENTA} ${x.toString} ${Console.RESET}"
    val totalFunction /*:(Int=>String)*/ =
      (x: Int) => s"${Console.MAGENTA} ${x.toString} ${Console.RESET}"

//    val totalFunctionWiithoutSyntaticSugar: SimpleFunctonOne[Int,String] = new SimpleFunctonOne[Int,String] {
//      override def apply(argument: Int): String = s"${Console.MAGENTA} ${argument.toString} ${Console.RESET}"
//    }

    val totalFunctionWiithoutSyntaticSugar = new Function1[Int, String] {
      override def apply(argument: Int): String =
        s"${Console.MAGENTA} ${argument.toString} ${Console.RESET}"
    }

    val partitialFunctionWiithoutSyntaticSugar: PartialFunction[Int, String] =
      new PartialFunction[Int, String] {
        override def isDefinedAt(x: Int): Boolean =
          if (x == 4) true else false
        override def apply(argument: Int): String =
          if (argument == 4)
            s"${Console.MAGENTA} ${argument.toString} ${Console.RESET}"
          else
            sys.error(
              s"Dude, you were supposed to call isDefined($argument) before calling apply"
            )
      }

    // type alias for partial function
    type ~>[-Input, +Output] = PartialFunction[Int, String]

//    val partialFunction: ~>[Int,String]= {
    val partialFunction: Int ~> String = {
      case argument if (argument == 4) =>
        s"${Console.MAGENTA} ${argument.toString} ${Console.RESET}"
    }

    val randomNumber = scala.util.Random.nextInt(10121)

    println(s"  $randomNumber")
    println(totalFunction(randomNumber))
    println(totalFunctionWiithoutSyntaticSugar(randomNumber))

    println(partitialFunctionWiithoutSyntaticSugar(4))

    // what partial function is used for

    val range = 1 to 10
//    range foreach println

    val function: Int => Int = _ + 1
    range map function foreach print
    range map totalFunction foreach print
//    range.map(function => println)
    println()
    println("filter")
    val predicate: Int => Boolean = _ == 4

    range filter predicate map totalFunction foreach println

    println("calling partial function")
    // filter and map at the same time
    range collect partitialFunctionWiithoutSyntaticSugar foreach println

    // i.g
    range.foreach(num =>
      if (partitialFunctionWiithoutSyntaticSugar.isDefinedAt(num))
        println(partitialFunctionWiithoutSyntaticSugar(num))
    )

    println("siya")
    range collect partialFunction foreach println

//    range map partitialFunctionWiithoutSyntaticSugar foreach println

//    class NameFunction1(name:String) extends SimpleFunctonOne[Int,String]{
//      override def apply(argument: Int): String = {
//        s"HI I'm $name. $argument"
//      }
//
//    }
//
//
//    val singleton:SimpleFunctonOne[Int,String] = new SimpleFunctonOne[Int,String] {
//      override def apply(argument: Int): String = s"Hi Im' a singleton :( $argument"
//    }

//    val alice:Chatty = new NameFunction("alice")
//    val bob:Chatty = new NameFunction("bob")
//    val alice:SimpleFunctonOne = new NameFunction1("alice")
//    val bob:SimpleFunctonOne = new NameFunction1("bob")
//
//    alice("How are you going?")
//    bob("Whats up?")
//    singleton("good")

    // use namefunction1 class to implement function literal
    // this is how functional litral are implemented

//
//    trait EventListner{
//      def EventOccured:Unit
//    }
//
//    class Button(name:String,listner:EventListner){
//      def click():Unit=listner.EventOccured
//
//    }
//
//    val listner:EventListner = new EventListner {
//      override def EventOccured: Unit = println("Event occured!")}
//
//
//    val button:Button = new Button("submit",listner)
//
//    button.click()

  }

}
