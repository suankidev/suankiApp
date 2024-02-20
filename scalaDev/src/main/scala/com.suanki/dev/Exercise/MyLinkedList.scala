package com.suanki.dev.Exercise

abstract class MyLinkedList {

  def head: Int

  def tail: MyLinkedList

  def add(element: Int): MyLinkedList

  def isEmpty: Boolean

  override def toString: String = s"[ $printList ]"

  def printList: String

}

object EmptyList extends MyLinkedList {

  override def head: Int = throw new NoSuchElementException

  override def tail: MyLinkedList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(element: Int): MyLinkedList =
    new TestList(element, EmptyList)

  override def printList: String = ""
}

class TestList(h: Int, t: MyLinkedList) extends MyLinkedList {

  override def head: Int = h

  override def tail: MyLinkedList = t

  override def add(element: Int): MyLinkedList =
    new TestList(element, this)

  override def isEmpty: Boolean = false

  override def printList: String =
    if (t.isEmpty) s"$h" else s"$h ${t.printList}"

}

object test extends App {

  var mylist = new TestList(1, new TestList(2, EmptyList))

  println(mylist)
  println(mylist.head)
  println(mylist.tail)
  println(mylist.add(4).head)
}
