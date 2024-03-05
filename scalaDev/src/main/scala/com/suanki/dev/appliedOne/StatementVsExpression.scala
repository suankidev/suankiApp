package com.suanki.dev.appliedOne

import scala.concurrent.{Await, Future}

class StatementVsExpression {

  class StdOut {

    // giving
    def out(x: String): StdOut = {

      println(x)
      this
    }

    def forLoopWithoutYield(): Unit = {

      for (i <- 1 to 3) println(i * 2)

      (1 to 4).foreach(i => println(i * 2))

      for (i <- 1 to 3; j <- 1 to 3) println(i * j)

      (1 to 3).foreach(i => (1 to 3).foreach(j => println(i * j)))

    }

    def forLoopWitYield(): Unit = {

      /** yield is alwys seq -> vector
        */
      val rslt = for (i <- 1 to 3) yield i * 2
      // this gets converted to
      val rsltCovnerted = (1 to 3).map(i => i * 2)

      val rslt1 = for (i <- 1 to 3; j <- 1 to 3) yield i * j

      println(rslt)
      println(rsltCovnerted)
      println(rslt1)

      val rslt1Converted = (1 to 3).flatMap(x => (1 to 3).map(y => x * y))
      println(rslt1Converted)

    }

    def forAndForYield(): Unit = {

      val fileLoc   = new java.io.File(getClass.getClassLoader.getResource("").toURI)
      val fileShare = new java.io.File(fileLoc.getParentFile, "basics").listFiles()

      lazy val getLines: java.io.File => Iterator[String] = f => scala.io.Source.fromFile(f).getLines()

      val forLineLength = for {
        file <- fileShare
        if file.getName.endsWith(".scala")
        line <- getLines(file)
        trimmed = line.trim
        if trimmed.matches(".*for.*")
      } yield trimmed.length

      println(forLineLength)
    }

    def forIsMoreThanALoop(): Unit = {}
    import scala.concurrent.duration._
    import scala.concurrent.ExecutionContext.Implicits.global

    val f1 = Future(1.0)
    val f2 = Future(2.0)
    val f3 = Future(3.0)

    val f4 = for {
      v1 <- f1
      v2 <- f2
      v3 <- f3
    } yield v1 + v2 + v3

    Await.result(f4, 10.seconds)

  }

  val arr: Array[String] = Array.empty[String]

  // does not return anything, hence try catch..is an expression
  try {
    println(arr.head)
  } catch {
    case _: NoSuchElementException => println("Array is empty!")
  } finally {
    println("Initializing class Stdout ")
    val stdout = new StdOut
    stdout.out("hello").out("i").out("am").out("there!")
//    stdout.forLoopWithoutYield()
//    stdout.forLoopWitYield()
//    stdout.forAndForYield()
    stdout.forIsMoreThanALoop()
  }

}
