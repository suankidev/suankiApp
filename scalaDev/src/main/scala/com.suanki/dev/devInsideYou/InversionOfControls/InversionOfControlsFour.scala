package com.suanki.dev.devInsideYou.InversionOfControls

final case class Device(
    wattPerSecond: Int,
    turnOn: () => Unit,
    turnOff: () => Unit
)

class ComplexEnergyMeterFour(device: Device) {

  private[this] var turnedOnAtMillis: Long = 0

  private[this] var _wattConsumedInTotal: Double = 0

  def startMeasuring(): Unit = {

    device.turnOn()
    turnedOnAtMillis = System.currentTimeMillis() // milli seconds since jan 1970

  }

  def stopMeasuring(): Unit = {
    device.turnOff()
    val duration          = System.currentTimeMillis() - turnedOnAtMillis
    val durationInSeconds = duration.toDouble / 1000
    wattConsumeInTotal += device.wattPerSecond * durationInSeconds
  }

  def wattConsumeInTotal: Double = _wattConsumedInTotal // getter

  private[this] def wattConsumeInTotal_=(newVal: Double): Unit = _wattConsumedInTotal = newVal // setter

}

//SomeComponent replace as companion objec
//staticdispatch as apply
//object ComplexEnergyMeterFour {
//
//  def apply(device: Any): ComplexEnergyMeterFour = device match {
//    case lightBulb: LightBulbFour =>
//      new ComplexEnergyMeterFour(lightBulb.wattsPerSecond, lightBulb.turnOn, lightBulb.turnOff)
//    case tv: TVFour =>
//      new ComplexEnergyMeterFour(tv.wattsPerSecond, tv.turnOn, tv.turnOff)
//    case _ =>
//      sys.error("not a correct device")
//  }
//
//
//}

class TVFour extends {
  val wattsPerSecond: Int = 500

  def toDevice: Device = {
    Device(
      this.wattsPerSecond,
      this.turnOn,
      this.turnOff
    )
  }

  def turnOn(): Unit = {
    println("tv on")
  }

  def turnOff(): Unit = {
    println("tv off")
  }

}

class LightBulbFour {
  def toDevice: Device = {
    Device(
      this.wattsPerSecond,
      this.turnOn,
      this.turnOff
    )
  }

  // light bulb
  val wattsPerSecond: Int = 100

  def turnOn(): Unit = {
    println("light bulb on")
  }

  def turnOff(): Unit = {
    println("ligh bulb off")
  }
}

object InversionOfControlsFour extends App {
  println("=" * 50)

  val tv        = new TVFour()
  val lightBulb = new LightBulbFour

  val lightMeter = new ComplexEnergyMeterFour(lightBulb.toDevice)

  lightMeter.startMeasuring()
  Thread.sleep(1000)
  lightMeter.stopMeasuring()
  println(lightMeter.wattConsumeInTotal)

  println()

  val tvMeter = new ComplexEnergyMeterFour(tv.toDevice)
  tvMeter.startMeasuring()
  Thread.sleep(1000)
  tvMeter.stopMeasuring()
  println(tvMeter.wattConsumeInTotal)

  println("=" * 50)

  /** here we are still passing three thing in each new device class i.g. TV and lightBulb
    */
}
