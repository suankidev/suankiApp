package com.suanki.dev.Exercise

abstract class MyList[+A] {

  /*
  head = first element of the list
  tail = remainder of the list
  isEmpty = is this list is empty
  add(int) => new list with this element added
  toString => a string representation of the list
   */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](item: B): MyList[B]
  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  // ??? return nothing
  override def head: Nothing         = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean      = true
  override def add[B >: Nothing](item: B): MyList[B] =
    new Cons[B](item, Empty)
  override def printElements: String = ""

}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A                         = h
  override def tail: MyList[A]                 = t
  override def isEmpty: Boolean                = false
  override def add[B >: A](item: B): MyList[B] = new Cons(item, this)
  override def printElements: String = {
    if (t.isEmpty) "" + h
    else s"${h},${t.printElements}"
  }
}

object ListTest extends App {
  println("+" * 100)
//// val list1 = new Cons(1, Empty)
////  println(list.isEmpty)
//  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(list.tail)
//  println(list.tail.head)
//  println(list.add(4).head)
//  println(list.toString)
  val listOfInteger: MyList[Int] =
    new Cons(1, new Cons[Int](2, new Cons[Int](3, Empty)))
  val listOfString: MyList[String] =
    new Cons("Scala", new Cons[String]("Java", new Cons[String]("Python", Empty)))

  println(listOfInteger.toString)
  println(listOfString.toString)

  println("+" * 100)
}
