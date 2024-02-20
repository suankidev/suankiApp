package com.suanki.tutorials.devInsideYou.InversionOfControls

class EnergyMeterSuanki(wattPerSecond: Int, turnDeviceOn: () => Unit, turnDeviceOff: () => Unit) {

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

object IocInversionOfControls extends App {
  println("=" * 50)
  val wattsPerSecondOfTV: Int = 500

  def turnTVOn(): Unit = {
    println("tv on")
  }

  def turnTVOff(): Unit = {
    println("tv off")
  }

  val energyMeter = new EnergyMeterSuanki(wattsPerSecondOfTV, turnTVOn, turnTVOff)
  energyMeter.startMeasuring()
  Thread.sleep(1000)
  energyMeter.stopMeasuring()
  println(energyMeter.wattConsumeInTotal)

  energyMeter.startMeasuring()
  Thread.sleep(1000)
  energyMeter.stopMeasuring()
  println(energyMeter.wattConsumeInTotal)

  // light bulb
  val wattsPerSecondOfLightBulb: Int = 100

  def turnLightBulbOn(): Unit = {
    println("light bulb on")
  }

  def turnLightBulbOff(): Unit = {
    println("ligh bulb off")
  }

  val lightMeter = new EnergyMeterSuanki(wattsPerSecondOfLightBulb, turnLightBulbOn, turnLightBulbOff)
  energyMeter.startMeasuring()
  Thread.sleep(1000)
  energyMeter.stopMeasuring()
  println(energyMeter.wattConsumeInTotal)

  println("=" * 50)
}
