package com.suanki.dev.devInsideYou.twodimentionaltime

import scala.concurrent.duration._

object Scala {
  def wait(delay: FiniteDuration): Unit = {
    Thread.sleep(delay.toMillis)
  }

  def loop(times: Int)(someCode: => Unit): Unit = {
    1 to times foreach (_ => someCode)
  }

  def tickUntilEnterIsPressed(
      interval: FiniteDuration
  )(somecode: => Unit): Unit = {
//    val timer:Timer = new Timer
//    scala.io.StdIn.readLine
//    timer.cancel()

  }
}
