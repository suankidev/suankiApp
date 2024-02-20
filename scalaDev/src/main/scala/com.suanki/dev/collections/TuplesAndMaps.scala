package com.suanki.tutorials.collections

object TuplesAndMaps extends App {

//tuple = finite ordered "lists"

  val aTuple  = new Tuple2(2, "hello, Scala")
  val aTuple1 = (2, "hello, Scala")
  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye java"))

  println(aTuple.swap) // swap the element in palce

//maps

  val aMap: Map[String, Int] = Map() // empty map

  val phonebook = Map(("Jim", "5555"), ("sujeet", "789"))

  val phonebook_or =
    Map("Jim" -> "5555", "sujeet" -> "789").withDefaultValue("-1")

  println(phonebook_or)

  println(phonebook.contains("Jim"))
  println(phonebook.apply("Jim")) // throgh if not exists

  // adding new pairing
  val newPairing   = "mary" -> "6678"
  val newPhoneBook = phonebook + newPairing

  println(newPhoneBook)

  // map , flatMap, filter
  println(newPhoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  println(newPhoneBook.filter(pair => pair._1.startsWith("J")))

  // mapValues //deprecated use map
  println(List(("Sujeet", "5555"), ("Jim", "5555")).toMap)

  val names = List("Sujeet", "bobs", "Angela", "Marchel")
  println(names.groupBy(name => name.charAt(0)))

}
