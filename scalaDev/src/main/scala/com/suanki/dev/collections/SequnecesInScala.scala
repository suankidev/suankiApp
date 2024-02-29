package com.suanki.dev.collections

object SequnecesInScala extends App {

  /** maps : map , flatMap,c ollect conversions : toArray, toList, toSeq size info: isEmtpyty,size, nonEmty tests:
    * exists, forall folds: foldLeft, foldRight, reduceLeft, reduceRight retrieval: head, find, tail
    */

  // Seq

  val aSequence = Seq(63, 34, 12, 43)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))              // 3
  println(aSequence ++ Seq(5, 6, 8)) // add
  println(aSequence.sorted)
  println(aSequence.max)
  println(aSequence)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // list
  val aList = List(1, 2, 5, 2, 6, 3, 2)

  val prepended    = 43 :: aList
  val preandappend = 43 +: aList :+ 45

  val apples5 = List.fill(5)("apples")

  println(apples5) // 5 time apple list

  println(aList.mkString("-|-"))

  // arrays
  val numbers = Array(1, 2, 5, 8, 9, 4)
  val treeElement =
    Array.ofDim[Int](3) // initialize with default value of int which is 0

  treeElement.foreach(println)

//mutation
  numbers(2) = 0 // update index 2 with value 0  - numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and seq

  val numberSeq: Seq[Int] = numbers.toSeq

  println(numberSeq) // wrappedArry : implicit conversion

}
