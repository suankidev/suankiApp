package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

class FactoryProducer {

  def getFactory(wearable: Boolean): AbstractFactory = {
    if (wearable)
      new WearableFactory
    else
      new HouseHoldFactory
  }

}
