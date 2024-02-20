package com.suanki.designPattern.creationalPattern.AbstractFactoryPattern

object Main {

  def main(args: Array[String]): Unit = {
//    val houseHoldFactory = new HouseHoldFactory
//    val wearableFactory = new WearableFactory

    val factoryProducer: FactoryProducer = new FactoryProducer
    val wearableFactory: AbstractFactory = factoryProducer.getFactory(true)
    val houseHOld: AbstractFactory       = factoryProducer.getFactory(wearable = false)

    val mobile: Device = wearableFactory.getObject("Mobile")
    val tv: Device     = houseHOld.getObject("tv")

    mobile.turnOn()
    tv.turnOn()
    tv.turnOff()

  }

}
