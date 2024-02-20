package com.suanki.tutorials.devInsideYou.inheritance

object Main {

  def main(args: Array[String]): Unit = {

    println("=" * 40)

    val tv: Device        = new TV
    val lightBulb: Device = new LightBulb

    val meter: EnergyMeter = new EnergyMeter(lightBulb)

    meter.startMeasuring()
    Thread.sleep(1000)
    meter.stopMeasuring()
    println(meter.wattsConsumedInTotal)

    meter.startMeasuring()
    Thread.sleep(1000)
    meter.stopMeasuring()
    println(meter.wattsConsumedInTotal)

    println("=" * 40)
  }
}
