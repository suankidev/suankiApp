package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

class HouseHoldFactory extends AbstractFactory {
  override def getObject(DeviceType: String): Device =
    DeviceType match {
      case "tv"        => new TV
      case "LightBulb" => new LightBulb
      case _           => throw new Exception(s"Device type ${DeviceType} is not defined")
    }
}
