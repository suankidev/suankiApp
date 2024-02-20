package com.suanki.designPattern.behaviourPattern.templatePattern

trait Device {
  val wattsPerSecond: Double = 200
  def initialMsg(): Unit     = println("Switching on...")
  def turnOn(): Unit
  def turnOff(): Unit
}
