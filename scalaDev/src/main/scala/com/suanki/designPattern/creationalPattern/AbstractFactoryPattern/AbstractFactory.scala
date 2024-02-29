package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

abstract class AbstractFactory {

  def getObject(DeviceType: String): Device

}
