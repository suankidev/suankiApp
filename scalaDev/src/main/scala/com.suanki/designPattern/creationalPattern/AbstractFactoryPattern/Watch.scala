package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

class Watch extends Device {
  override def turnOn(): Unit = {
    println("Watch is on")
  }

  override def turnOff(): Unit = {
    println("Watch is off")
  }
}
