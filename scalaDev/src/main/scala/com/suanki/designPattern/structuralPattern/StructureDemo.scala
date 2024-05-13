package com.suanki.designPattern.structuralPattern

//This is the abstraction
//It represent a first in first out collection

trait FifoCollection[T] {
  // add element to the collection
  def offer(element: T): Unit

  // remove and return first elemnt from collection
  def poll(): T
}

trait LinkedList[T] {
  def addFirst(element: T): T
  def removeFirst(element: T): T
  def removeLast(element: T): T
  def getSize(): Int
}
//
//class SinglyLinkedList[T] extends LinkedList[T] {
//
//  class Node(val data: Any, val next: Node)
//
//  private var last: Node = null
//  private var top: Node  = null
//  private var size       = 0
//
//  override def addFirst(element: T): Unit = {
//    if (top == null) {
//      top = new Node(element, null)
//      last = top
//    } else {
//      top = new Node(element, top)
//    }
//    size += 1
//
//  }
//
//  override def removeFirst(element: T): T = {
//    val temp: T = top.data.asInstanceOf[T]
//    if (top == null) {
//      null
//    }
//    if (top.next == null) {
//      top = top.next
//    } else {
//      top = null
//      last = null
//    }
//    size -= 1
//    temp
//  }
//
//  def addLast(element: T): Unit = {
//    if (last == null)
//      last = top = new SinglyLinkedList[T]#Node(element, null)
//    else {
//      last.next = new SinglyLinkedList[T]#Node(element, null)
//      last = last.next
//    }
//    size += 1
//  }
//
//  def removeLast(): T = {
//    if (last == null) null
//    if (top eq last) {
//      @SuppressWarnings(Array("unchecked")) val temp = top.data.asInstanceOf[T]
//      top = last = null
//      size -= 1
//      temp
//    }
//    // since we don't have a back pointer
//    var temp = top
//    while ({
//      temp.next ne last
//    }) temp = temp.next
//    @SuppressWarnings(Array("unchecked")) val result = last.data.asInstanceOf[T]
//    last = temp
//    last.next = null
//    size -= 1
//    result
//  }
//
//}

object StructureDemo extends App {

  println("=" * 50)

  println("=" * 50)
}
