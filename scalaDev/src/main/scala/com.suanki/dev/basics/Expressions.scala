package com.suanki.tutorials.basics

object Expressions extends App {

  val x = 1 + 2 // EXPRESSION

  println(x)
  println(2 + 3 * 4)

  // Instrunction:  (DO)
  // Expression:(TYPE) that has a value or and type

  // if expression

  val aConditiotn = true

  val aCondtitiondedValue =
    if (aConditiotn) 5 else 3 // it gives a value it's expressoin

  println(aCondtitiondedValue)

  println(if (aConditiotn) 5 else 3)

  var i = 0

  while (i < 10) {

    println(i)
    i += 1
  }

  // don't use while b/c sideeffect  while returns unit

  // side effects: println, whiles, reassigning

  val aWieredValue = while (i < 10) {

    println(i)
    i += 1
  }

  println((aWieredValue))

  // Code Blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "GoodBuy"
  } // valu of whole block is last expression

  println(aCodeBlock)

}
