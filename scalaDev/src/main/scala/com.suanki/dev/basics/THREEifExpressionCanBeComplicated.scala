package com.suanki.dev.basics

import com.suanki.dev.Exercise.show

object THREEifExpressionCanBeComplicated extends App {

  show()

  if ({ true }) {
    println("I'm Happy!")
  }

  lazy val isAdditional = true

  if (isAdditional) {
    println("is Additional is true")
  } else {
    println("is Additional is false")
  }

  val isSunnyDay = true
  val isRainyDay = true

  val result = {
    if (isSunnyDay)
      "I'm Happy"
    else if (isRainyDay)
      "i'm said because it's raining"
    else
      "I'm Sad "
  }

  // Accessing something from different scope

  {

    val isSunnyDay1 = true
    val isRainyDay1 = true

    val result1 = {
      if (isSunnyDay1)
        "I'm Happy"
      else if (isRainyDay1)
        "i'm said because it's raining"
      else
        "I'm Sad "
    }

  }

//  result1 : not accessible here

//WE are accessing show from diff scope here
  show()

}
