package com.suanki.dev.devInsideYou.InversionOfControls

class ComplexEnergyMeterOne(wattPerSecond: Int, turnDeviceOn: () => Unit, turnDeviceOff: () => Unit) {

  private[this] var turnedOnAtMillis: Long = 0

  private[this] var _wattConsumedInTotal: Double               = 0
  def wattConsumeInTotal: Double                               = _wattConsumedInTotal          // getter
  private[this] def wattConsumeInTotal_=(newVal: Double): Unit = _wattConsumedInTotal = newVal // setter

  // comment b/c won't work for other device except lightbulb moved to static dispatch
//  def this(device: LightBulb) = {
//    this(device.wattsPerSecond, device.turnOn, device.turnOff)
//  }

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

object SomeComponent {

  def staticDispatch(device: Any): ComplexEnergyMeterOne = {
    if (device.isInstanceOf[LightBulb]) {
      val lightBulb: LightBulb = device.asInstanceOf[LightBulb]
      new ComplexEnergyMeterOne(lightBulb.wattsPerSecond, lightBulb.turnOn, lightBulb.turnOff)
    } else if (device.isInstanceOf[TV]) {
      val tv: TV = device.asInstanceOf[TV]
      new ComplexEnergyMeterOne(tv.wattsPerSecond, tv.turnOn, tv.turnOff)
    } else {
      sys.error("not a correct device")
    }
  }
}

class TV extends {
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

object InversionOfControlsThree extends App {
  println("=" * 50)

  val tv        = new TV
  val lightBulb = new LightBulb

  val lightMeter = SomeComponent.staticDispatch(lightBulb)

  lightMeter.startMeasuring()
  Thread.sleep(1000)
  lightMeter.stopMeasuring()
  println(lightMeter.wattConsumeInTotal)

  val tvMeter = SomeComponent.staticDispatch(tv)
  tvMeter.startMeasuring()
  Thread.sleep(1000)
  tvMeter.stopMeasuring()
  println(tvMeter.wattConsumeInTotal)

  println("=" * 50)

  /** *
    *
    * Every time we add new device we need to add pattern match(code in SomeComponent for each type of device)
    */
}
