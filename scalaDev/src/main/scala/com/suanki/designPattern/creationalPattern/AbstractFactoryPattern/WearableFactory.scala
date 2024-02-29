package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

class WearableFactory extends AbstractFactory {
  override def getObject(DeviceType: String): Device =
    DeviceType match {
      case "Watch"  => new Watch
      case "Mobile" => new Mobile
      case _        => throw new Exception(s"Device type ${DeviceType} is not defined")
    }
}
