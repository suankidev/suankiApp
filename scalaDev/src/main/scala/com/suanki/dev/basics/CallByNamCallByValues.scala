package com.suanki.dev.basics

object CallByNamCallByValues extends App {

  def calledByValue(x: Long): Unit = {

    println("by value " + x)
    println("by value " + x)
  }

  // => represent by name
  def calledByName(x: => Long): Unit = {

    println("by Name value " + x)
    println("by Name value " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(
    System.nanoTime()
  ) // evaluated each time and also delay unless it's used

}
