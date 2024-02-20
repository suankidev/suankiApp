package com.suanki.designPattern.behaviourPattern.templatePattern

class TV extends Device {

  override val wattsPerSecond: Double = 200
  override def turnOn(): Unit = {
    initialMsg()
    println("TV turned on")
  }

  override def turnOff(): Unit = println("TV turned off")
}
