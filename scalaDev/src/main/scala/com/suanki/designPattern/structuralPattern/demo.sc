trait Shape {
  val height: Double
  val width: Double
  def area(): Double
}

class Rectangle extends Shape {
  val height         = 10.9
  val width          = 38.89
  def area(): Double = width * height
}

class Squares extends Shape {
  val height         = 4
  val width          = 4
  def area(): Double = math.sqrt(width)
}

class Window[T <: Shape](shape: T) {

  def area(): Double = shape.area()
}

new Window[Shape](new Rectangle).area()

//composition
class Building {
  val address: String = "Kharadi, Pune"
  val room: Room      = new Room
  class Room {
    def getAddress: String = Building.this.address
  }

}

new Building().room.getAddress

//aggregation
class Wheel(typeOfWheel: String) {}

class Car {
  val noOfWheels: List[Wheel] = List(new Wheel("frontWheel"), new Wheel("Rear Wheel"))

}
