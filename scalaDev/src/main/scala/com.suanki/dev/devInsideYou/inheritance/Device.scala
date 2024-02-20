package com.suanki.tutorials.devInsideYou.inheritance

trait Device {

  val wattPerSecond: Double

  private[this] var _isOn: Boolean = false

  protected[this] def isOn_=(newVal: Boolean): Unit = _isOn = newVal

  def isOn: Boolean = _isOn

  private[this] var _isOff: Boolean = !_isOn

  protected[this] def isOff_=(newVal: Boolean): Unit = _isOn = !newVal

  def isOff: Boolean = !isOn

  def turnedOn(): Unit = {
    if (isOff) {
      isOn = true
      actuallyTurnedOn
    } else
      sys.error(" already on")
  }

  def turnedOff(): Unit = {
    if (isOn) {
      isOff = true
      actuallyTurnedOff
    } else {
      sys.error("already Off")
    }
  }

  protected[this] def actuallyTurnedOn(): Unit
  protected[this] def actuallyTurnedOff(): Unit

}
