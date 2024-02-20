package com.suanki.designPattern.creationalPattern.factoryPattern

class ShapeFactory {

  def getShape(shapeType: String): Shape =
    shapeType match {
      case "Rectangle" => new Rectangle
      case "Circle"    => new Circle
      case _           => throw new Exception("Shape is not defined yet!")
    }
}
