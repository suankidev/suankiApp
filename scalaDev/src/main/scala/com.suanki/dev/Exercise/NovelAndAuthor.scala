package com.suanki.tutorials.Exercise

object NovelAndAuthor extends App {

  val author = new Writer("Ram Dhari Singh", "Dinkar", 1956)
  val novel  = new Novel("Kala Namak", 1988, author)

  println(novel.authorAge)

  val counter = new CounterVal

  println(counter.inc.count)
  println(counter.inc.inc.count)
}

class CounterVal(val count: Int = 0) {

  def inc = {
    println("incrementing")
    new CounterVal(count + 1)
  } // immutability

  def inc(n: Int): CounterVal = {
    if (n <= 0) {
      this
    } else inc.inc(n - 1) // immutability
  }

  def dec = {
    println("Decrementing")
    new CounterVal(count - 1)
  }

  def dec(n: Int): CounterVal = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

}

class Writer(firstName: String, surname: String, val year: Int) {

  def fullName = s"${firstName} ${surname}"

}

class Novel(name: String, year: Int, author: Writer) {

  def authorAge = year - author.year

  def isWrittenBy(author: Writer) = author == this.author

  def copy(newYear: Int): Novel = new Novel(name, newYear, author)

}
