package com.suanki.designPattern.behaviourPattern.templatePattern

object Main {

  val lightBulb: Device = new LightBulb
  lightBulb.turnOn()
  lightBulb.turnOff()

  val tv: Device = new TV
  tv.turnOn()
  tv.turnOff()
}
