package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

class TV extends Device {
  override def turnOn(): Unit = {
    println("TV is on")
  }

  override def turnOff(): Unit = {
    println("TV is off")
  }
}
