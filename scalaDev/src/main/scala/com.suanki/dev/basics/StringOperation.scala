package com.suanki.dev.basics

object StringOperation extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.endsWith("Hello"))
  println(str.replace("Hello", "test"))
  println(str.trim)
  println(str.length)

  val aNumberString = "45"
  val anumber       = aNumberString.toInt

  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // Scala specifice: String interpolatores

  println(s"hello my name is ${anumber}")

  // f -interplators

  val speed = 1.2f
  val name  = "ford"
  val myth  = f"$name can eat $speed%2.2f"

  print(myth)

}
