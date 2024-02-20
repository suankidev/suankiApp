package com.suanki.designPattern.behaviourPattern.templatePattern

class LightBulb extends Device {
  override val wattsPerSecond: Double = 100
  override def turnOn(): Unit = {
    initialMsg()
    println("LightBulb turned on")
  }
  override def turnOff(): Unit = println("LightBulb turned off")
}
