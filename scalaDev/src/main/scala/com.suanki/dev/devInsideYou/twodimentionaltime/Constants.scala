package com.suanki.tutorials.devInsideYou.twodimentionaltime

import scala.concurrent.duration._

object Constants {

  val FewSeconds: Int          = 3
  val Interval: FiniteDuration = 1.second
  val LinesToGoUp: Int         = Int.MaxValue
  val TimePattern: String      = "HH:mm:ss"

}

object TickForFewSeconds {

  def main(args: Array[String]) = {
    Scala.loop(times = Constants.FewSeconds) {
      Terminal.clearCanvas
      Terminal.goUp(Constants.LinesToGoUp)
      TwoDimensionalTime.showCurrent(Constants.TimePattern)
      Scala.wait(Constants.Interval)
    }

  }
}

//object TickUntil{
//
//  def main(args:Array[String])=
//  {
//    Scala.tickUntilEnterIsPressed(Constants.Interval){
//
//      Terminal.clearCanvas
//      Terminal.goUp(
//        Constants.LinesToGoUp
//      )
//      TwoDimensionalTime.showCurrent(Constants.TimePattern)
//
//    }
//
//  }
//}
