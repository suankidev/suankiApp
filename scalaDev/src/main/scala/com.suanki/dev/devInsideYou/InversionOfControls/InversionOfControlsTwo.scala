package com.suanki.tutorials.devInsideYou.InversionOfControls

class ComplexEnergyMeter(wattPerSecond: Int, turnDeviceOn: () => Unit, turnDeviceOff: () => Unit) {

  private[this] var turnedOnAtMillis: Long = 0

  private[this] var _wattConsumedInTotal: Double               = 0
  def wattConsumeInTotal: Double                               = _wattConsumedInTotal          // getter
  private[this] def wattConsumeInTotal_=(newVal: Double): Unit = _wattConsumedInTotal = newVal // setter

  def startMeasuring(): Unit = {

    turnDeviceOn()
    turnedOnAtMillis = System.currentTimeMillis() // milli seconds since jan 1970

  }

  def stopMeasuring(): Unit = {
    turnDeviceOff()
    val duration          = System.currentTimeMillis() - turnedOnAtMillis
    val durationInSeconds = duration.toDouble / 1000
    //    _wattConsumedInTotal += wattPerSecond * durationInSeconds
    wattConsumeInTotal += wattPerSecond * durationInSeconds
    //    wattConsumeInTotal_=(wattConsumeInTotal + (wattPerSecond * durationInSeconds))
  }

}

object InversionOfControlsTwo extends App {
  println("=" * 50)

  class TV {
    val wattsPerSecond: Int = 500

    def turnOn(): Unit = {
      println("tv on")
    }

    def turnOff(): Unit = {
      println("tv off")
    }

  }

  class LightBulb {
    // light bulb
    val wattsPerSecond: Int = 100

    def turnOn(): Unit = {
      println("light bulb on")
    }

    def turnOff(): Unit = {
      println("ligh bulb off")
    }
  }

  val tv        = new TV
  val lightBulb = new LightBulb

  val energyMeter = new ComplexEnergyMeter(tv.wattsPerSecond, tv.turnOn, tv.turnOff)
  energyMeter.startMeasuring()
  Thread.sleep(1000)
  energyMeter.stopMeasuring()
  println(energyMeter.wattConsumeInTotal)

  energyMeter.startMeasuring()
  Thread.sleep(1000)
  energyMeter.stopMeasuring()
  println(energyMeter.wattConsumeInTotal)

  val lightMeter =
    new ComplexEnergyMeter(lightBulb.wattsPerSecond, lightBulb.turnOn, lightBulb.turnOff)

  lightMeter.startMeasuring()
  Thread.sleep(1000)
  lightMeter.stopMeasuring()
  println(lightMeter.wattConsumeInTotal)

  /** * we are still passing three parameter to the class
    *
    * we need to find a way to pass one params and it should work
    *
    * see part-3
    */
  println("=" * 50)
}
