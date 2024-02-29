package com.suanki.dev.devInsideYou.part15

//com.suanki.dev.devInsideYou.part15.ValuePreposition

object ValuePreposition {

  def main(args: Array[String]): Unit = {

    code(args)
  }

  def code(args: Array[String]): Unit = {

    // rarely used
    type Structure = {
      def wattPerSecond: Int
      def turnOn(): Unit
      def turnOff(): Unit
    }
    trait Device {
      def wattPerSecond: Int
      def turnOn(): Unit
      def turnOff(): Unit
    }

    trait Entertains
//   final case class Device(wattPerSecond:Int, turnDeviceOn: () => Unit,
//                           turnDeviceOff: () => Unit)

//  class EnergyMeter(device:Structure){
    class EnergyMeter(device: Device) {

      private[this] var turnedOnAtMills: Long        = 0L
      private[this] var _wattConsumedInTotal: Double = 0L
      def wattsConsumedInTotal: Double               = _wattConsumedInTotal
      // private[this] def wattsConsumedInTotal(newValue:Double):Unit = _wattConsumedInTotal = newValue
      private[this] def wattsConsumedInTotal_=(newValue: Double): Unit =
        _wattConsumedInTotal = newValue

      def startMeasuring(): Unit = {
        device.turnOn()
        turnedOnAtMills = System.currentTimeMillis // milliseconds since jan 1, 1970
      }

      def stopMeasuring(): Unit = {
        device.turnOff()
        val duration          = System.currentTimeMillis() - turnedOnAtMills
        val durationInSeconds = duration.toDouble / 1000
        // _wattConsumedInTotal += ( device.wattPerSecond * durationInSeconds)
        wattsConsumedInTotal_=(
          _wattConsumedInTotal + (device.wattPerSecond * durationInSeconds)
        )
      }

    }

//    object SomeComponent{
//      def staticDispatch(device:Any):EnergyMeter={
//        if(device.isInstanceOf[LightBulb]){
//          val lightBulb:LightBulb = device.asInstanceOf[LightBulb]
//          new EnergyMeter(lightBulb.wattPerSecond, lightBulb.turnOn, lightBulb.turnOff)
//        }
//        else if(device.isInstanceOf[TV]){
//          val tv:TV = device.asInstanceOf[TV]
//          new EnergyMeter(tv.wattPerSecond, tv.turnOn, tv.turnOff)
//        }
//        else
//          sys.error("Unknown device")
//      }
//    }

//    object EnergyMeter{
//      def apply(device:Any):EnergyMeter= device match {
//        case lightBulb: LightBulb=>
//          new EnergyMeter(Device(lightBulb.wattPerSecond, lightBulb.turnOn, lightBulb.turnOff))
//        case tv:TV=>
//          new EnergyMeter(Device(tv.wattPerSecond, tv.turnOn, tv.turnOff))
//        case _ =>
//          sys.error("Unknown device")
//      }
//    }

    class TV extends Device with Entertains {
//         def toDevice:Device =  Device(
//           this.wattPerSecond,
//           this.turnOn,
//           this.turnOff
//         )
      val wattPerSecond: Int = 500

      def turnOn(): Unit = {
        println("tv on")
      }

      def turnOff(): Unit = {
        println("tv off")
      }

    }

    class LightBulb extends Device {
//      def toDevice:Device =  Device(
//        this.wattPerSecond,
//        this.turnOn,
//        this.turnOff
//      )
      val wattPerSecond: Int = 100

      def turnOn(): Unit = {
        println("light bulb on")
      }

      def turnOff(): Unit = {
        println("light bulb  off")
      }

    }

    val lightBulb: Device = new LightBulb
    val tv: Device        = new TV

    val energyMeter = new EnergyMeter(lightBulb)

//    val energyMeter=EnergyMeter(lightBulb)

    energyMeter.startMeasuring()
    Thread.sleep(1000)
    energyMeter.stopMeasuring()
    println(energyMeter.wattsConsumedInTotal)

//    energyMeter.startMeasuring()
//    Thread.sleep(1000)
//    energyMeter.stopMeasuring()
//    println(energyMeter.wattsConsumedInTotal)

  }

}
