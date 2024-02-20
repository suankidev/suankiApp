package com.suanki.tutorials.devInsideYou

object HOF extends App {

  /** hof function that accept other function as input function that produce other function as output
    *
    * -> every HOF can control the flow of passed function, may be you can call 2 times
    */

  println("=" * 50)

  val d2: Int => Int = (x: Int) => x + 8
  val d1: Int => Int = x => x + 8
  val x              = (x: Int) => x + 10
  val z              = (x: Int) => x + 10: Int

  // function literal
  val d: Int => Int = x => x + 5
//  val f1:()=>Unit=()=>"hello"
  val g: (Int, Int) => Int        = (x: Int, y: Int) => x + y
  val k: (Int, Int) => Int => Int = (x: Int, y: Int) => d

  println(d(10))
  println(k(5, 10)(5))

  // Anonymous
  val e  = (x: Int) => x + 6
  val f  = () => "hi"
  val g1 = (x: Int, y: Int) => x + y

  def code(args: Array[String]): Unit = {
    def plusFive(input: Int): Int    = input + 5
    def devideByTwo(input: Int): Int = input / 2

    def rendered(funName: String, argument: Int, fun: Int => Int) =
      s"Function ${funName} \t Argument $argument  \t Result: ${fun(argument)}"

    println(rendered("plushFive", 10, plusFive))
    println(rendered("plushFive", 10, input => input + 10))
    println(rendered("devideByTwo", 10, input => input / 3))
    println(rendered("devideByTwo", 10, devideByTwo))
  }

  code(args)

  def practicalCode(args: Array[String]): Unit = {

    def createStgDF(spark: String = "sparkSession"): String = {

      "stgDF"
    }

    def createFinalDF(spark: String = "sparkSession"): String = {
      "finalDF"
    }

    def dataFrameCreator(
        dfType: String = "stg",
        spark: String,
        fun: String => String
    ): String = {
      fun(spark)
    }

    val stgDF   = dataFrameCreator("stg", "sparkSession", createStgDF)
    val finalDf = dataFrameCreator("stg", "sparkSession", createFinalDF)

    println(stgDF)
    println(finalDf)
  }

  practicalCode(args)

  def loop(code: () => Unit): Unit = {

    1 to 10 foreach (_ => code())
  }
//  loop(() => ())
  println()
//  loop(() => println("Hello"))

  // here we removed () then we can remove parenthesis
  // also passing an argument by Name
  def loopNew(code: => Unit): Unit = {

    1 to 10 foreach (_ => code)
  }

  loopNew(println("hi"))

  // creating a method
  def show1(x: Int) = println(x)
  def code3(argument: Range, fun: Int => Unit): Unit = {

    argument foreach (
      fun
    )

  }

//  code3(1 to 10, show1)
//
  println("=" * 50)

}
