package com.suanki.tutorials.devInsideYou.inheritance

class LightBulb extends Device {
  override val wattPerSecond: Double = 100

  override protected[this] def actuallyTurnedOn(): Unit = {
    println("Bulb on")
  }

  override protected[this] def actuallyTurnedOff(): Unit = {
    println("Bulb off")

  }
}
