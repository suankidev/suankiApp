package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

class Mobile extends Device {
  override def turnOn(): Unit = {
    println("Mobile is on")
  }

  override def turnOff(): Unit = {
    println("Mobile is off")
  }
}
