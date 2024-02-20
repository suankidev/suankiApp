package com.suanki.tutorials.devInsideYou.traitPower

object Modification {

  trait Spoiler extends Base.Car {
    // putting abstract keyword, since super.topSpeedInkmPerHour is abstract itself
    // this is very rare, only available in scala
    abstract override def topSpeedInKmPerHour: Int = (super.topSpeedInKmPerHour * 1.02).toInt
  }

  trait EngineControlUnit extends Core.OrdinaryCar {
    override def topSpeedInKmPerHour: Int = (super.topSpeedInKmPerHour * 1.5).toInt
  }

  trait TurboCharging extends Core.OrdinaryCar {
    override def topAccelerationInRpm: Int = (super.topAccelerationInRpm * 1.25).toInt
  }

}
