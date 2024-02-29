package com.suanki.dev.devInsideYou.inheritance

class EnergyMeter(device: Device) {
  private[this] var turnedOnAtMillis: Long        = 0
  private[this] var _wattsConsumedInTotal: Double = 0
  def wattsConsumedInTotal: Double                = _wattsConsumedInTotal

  private[this] def wattsConsumedInTotal_=(newValue: Double): Unit =
    _wattsConsumedInTotal = newValue

  def startMeasuring(): Unit = {
    device.turnedOn()
    turnedOnAtMillis = System.currentTimeMillis
  }

  def stopMeasuring(): Unit = {
    device.turnedOff()
    val durationInMillis  = System.currentTimeMillis - turnedOnAtMillis
    val durationInSeconds = durationInMillis.toDouble / 1000

    // both these two lines are same
//    wattsConsumedInTotal_=(_wattConsumedInTotal + device.wattPerSecond * durationInSeconds)
    wattsConsumedInTotal += device.wattPerSecond * durationInSeconds
  }

}
