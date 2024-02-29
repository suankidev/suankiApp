package com.suanki.dev.devInsideYou.anonymousPartialFunction

object UseCases extends App {

  def code(args: Array[String]): Unit = {

    type ~>[-Input, +Output] = PartialFunction[Input, Output]

    def toRomanNumeralDigit(number: Int): String = {
      //    val partialFunction:Int=>String = {
      number match {
        case 0 => "0: N"
        case 1 => "1: I"
        case 2 => "2: II"
        case 3 => "3: III"
        case 4 => "4: IV"
        case 5 => "5: V"
        case 6 => "6: VI"
        case 7 => "7: VII"
        case 8 => "8: VIII"
        case 9 => "9: IX"
      }

    }

    //    partialFunction(number)
    val onlyOddDigits: Int ~> String = {
      case 0 => "0: N"
      case 2 => "2: II"
      case 4 => "4: IV"
      case 6 => "6: VI"
      case 8 => "8: VIII"
    }

    val OnlyEvenDigits: Int ~> String = {
      case 0 => "0: N"
      case 1 => "1: I"
      case 3 => "3: III"
      case 5 => "5: V"
      case 7 => "7: VII"
      case 9 => "9: IX"
    }

    def toRomanNumeralDigitOrElse(number: Int): String = {
      val partialFunction: Int ~> String = OnlyEvenDigits orElse onlyOddDigits
      partialFunction(number)
    }

    0 until 10 map toRomanNumeralDigit foreach println
    println()
    0 until 10 map toRomanNumeralDigitOrElse foreach println

    println("")
    0 until 10 map toRomanNumeralDigit foreach println

    val test: PartialFunction[Int, String] = new ~>[Int, String] {

      override def apply(v1: Int): String = s"$v1 apply"

      override def isDefinedAt(x: Int): Boolean = x >= 5

    }

    println(test(10))
  }

  def testMyCode: Unit = {

    type ~>[-Input, +Output] = PartialFunction[Input, Output]

    def getEven: Int ~> Int = {
      case 2  => println("inside 2"); 2
      case 4  => 4
      case 6  => 6
      case 8  => 8
      case 10 => 10
    }

    def getOdd: Int ~> Int = {
      case 3 => 3
      case 5 => 5
      case 7 => 7
    }

    def getFinalVal: Int ~> Int = getEven orElse getOdd

    0 until 5 collect getFinalVal foreach println

  }

}
