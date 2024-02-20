package com.suanki.tutorials.devInsideYou.caseClasses

sealed abstract class MayBe[+A] {

  def get: A
  def isEmpty: Boolean
}

final case class SureThing[+A](value: A) extends MayBe[A] {

  override def get: A = value

  override def isEmpty: Boolean = false
}

case object NotReally extends MayBe[Nothing] {
  override def get: Nothing = throw new NoSuchElementException

  override def isEmpty: Boolean = true
}
