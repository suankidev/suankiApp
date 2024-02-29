package com.suanki.dev.devInsideYou.InversionOfControls

//abstract class ElectricalDevice {
//
//  def wattsPerSecond: Int = {
//    sys.error("pass some value")
//  }
//  def turnOn: Unit = {
//    sys.error("turned on smthng")
//  }
//
//  def turnOff: Unit = {
//    sys.error("turned off smthng")
//  }
//
//}
trait ElectricalDevice {
  def wattsPerSecond: Int
  def turnOn: Unit
  def turnOff: Unit

}

trait Entertainment
trait Expensive

class ComplexEnergyMeterFive(device: ElectricalDevice) {

  private[this] var turnedOnAtMillis: Long = 0

  private[this] var _wattConsumedInTotal: Double = 0

  def startMeasuring(): Unit = {

    device.turnOn
    turnedOnAtMillis = System.currentTimeMillis() // milli seconds since jan 1970

  }

  def stopMeasuring(): Unit = {
    device.turnOff
    val duration          = System.currentTimeMillis() - turnedOnAtMillis
    val durationInSeconds = duration.toDouble / 1000
    wattConsumeInTotal += device.wattsPerSecond * durationInSeconds
  }

  def wattConsumeInTotal: Double = _wattConsumedInTotal // getter

  private[this] def wattConsumeInTotal_=(newVal: Double): Unit = _wattConsumedInTotal = newVal // setter

}

class TVFive extends ElectricalDevice with Entertainment {

  override def wattsPerSecond: Int = 500

  override def turnOn: Unit = {
    println("tv on")
  }

  override def turnOff: Unit = {
    println("tv off")
  }

}

class LightBulbFive extends ElectricalDevice {
  // light bulb
  override def wattsPerSecond: Int = 100

  override def turnOn: Unit = {
    println("light bulb on")
  }

  override def turnOff: Unit = {
    println("light bulb off")
  }
}

object InversionOfControlsFive extends App {
  println("=" * 50)

  val lightBulb: ElectricalDevice = new LightBulbFive
  val tv: ElectricalDevice        = new TVFive
  val lightMeter                  = new ComplexEnergyMeterFive(lightBulb)

  lightMeter.startMeasuring()
  Thread.sleep(1000)
  lightMeter.stopMeasuring()
  println(lightMeter.wattConsumeInTotal)

  println()

  val tvMeter = new ComplexEnergyMeterFive(tv)
  tvMeter.startMeasuring()
  Thread.sleep(1000)
  tvMeter.stopMeasuring()
  println(tvMeter.wattConsumeInTotal)

  println("=" * 50)

  /** here we are still passing three thing in each new device class i.g. TV and lightBulb
    */
}
