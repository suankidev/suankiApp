package com.suanki.tutorials.devInsideYou.inheritance

class TV extends Device {
  override val wattPerSecond: Double = 500

  override protected[this] def actuallyTurnedOn(): Unit = {
    println("TV on")
  }

  override protected[this] def actuallyTurnedOff(): Unit = {
    println("TV off")
  }
}
