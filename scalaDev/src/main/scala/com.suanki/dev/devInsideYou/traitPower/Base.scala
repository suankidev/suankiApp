package com.suanki.dev.devInsideYou.traitPower

object Base {

  abstract class Car {
    def model: String
    def topSpeedInKmPerHour: Int
    def topAccelerationInRpm: Int

    override def toString: String = {
      val brand = getClass.getSimpleName
      brand + " " + model + " " + topSpeedInKmPerHour + " " + topAccelerationInRpm
    }
  }
}
