abstract class Device {

  def turnOn: Unit
  def trunOff: Unit
}

class Tv extends Device {
  override def turnOn: Unit = ???

  override def trunOff: Unit = ???
}

class Bulb extends Device {
  override def turnOn: Unit = ???

  override def trunOff: Unit = ???
}
