package com.suanki.tutorials.functionalp

object MapFlatMapFilter extends App {

  val list = List(1, 2, 3)
  println(list)

  println(list.head)
  println(list.tail)

  println(list.map(x => s"${x} is a number"))
  println(list.map((element: Int) => element + 1))

  // filter
  println(list.filter(_ * 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combination b/w two lists
  val numbers = List(1, 2, 3, 4)
  val chairs  = List('a', 'b', 'c', 'd', 'e')

  val combination = numbers.flatMap(n => chairs.map(c => "" + c + n))

  println(combination)

  println(numbers.flatMap(x => List(x, x + 10)))

  // foreach
  numbers.foreach(println)

  // for-comrehension
  val forCombination = for {
    n <- numbers if n > 3
    c <- chairs
  } yield s"$c $n"
  println(forCombination)

  // overload

  list.map { x =>
    x * 2
  }

}
