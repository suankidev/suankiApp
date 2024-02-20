package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

class LightBulb extends Device {
  override def turnOn(): Unit = {
    println("LightBulb is on")
  }

  override def turnOff(): Unit = {
    println("LightBulb is off")
  }
}
