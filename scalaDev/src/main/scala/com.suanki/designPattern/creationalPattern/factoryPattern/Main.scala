package com.suanki.designPattern.creationalPattern.factoryPattern

object Main {

  def main(args: Array[String]): Unit = {

    println("=" * 50)
    val factory = new ShapeFactory

    val rectangle = factory.getShape("Rectangle")
    val circle    = factory.getShape("Circle")

    rectangle.draw()
    circle.draw()

    println("=" * 50)
  }
}
