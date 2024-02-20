package com.suanki.designPattern.creationalPattern.factoryPattern

class Rectangle extends Shape {
  override def draw(): Unit = {
    println("inside Rectangle : draw()")
  }
}
