package com.suanki.dev.devInsideYou.traitPower

object Core {

  class OrdinaryCar(override val model: String) extends Base.Car {
    override def topSpeedInKmPerHour: Int  = 220
    override def topAccelerationInRpm: Int = 8000
  }

  class SportCar(override val model: String) extends Base.Car {
    override def topSpeedInKmPerHour: Int  = 300
    override def topAccelerationInRpm: Int = 11000
  }

}
