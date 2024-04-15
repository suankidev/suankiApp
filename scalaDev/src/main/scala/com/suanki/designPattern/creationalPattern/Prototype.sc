import javafx.geometry.Point3D

abstract class GameUnit extends Cloneable {

  private var position: Point3D = Point3D.ZERO;

  def apply(x: Float, y: Float, z: Float): Unit = {
    position = new Point3D(x, y, z)
  }

  @throws(classOf[CloneNotSupportedException])
  override def clone(): GameUnit = {
    val copy = super.clone().asInstanceOf[GameUnit] // shallow copy
    copy.initialize()
    copy
  }

  def move(dirction: Point3D, distance: Float): Unit = {
    var finalMove: Point3D = dirction.normalize()
    finalMove = finalMove.multiply(distance)
    position = position.add(finalMove)
  }

  protected def initialize(): Unit = {
    this.position = Point3D.ZERO
    reset
  }

  def getPosition = this.position
  def reset: Unit

}

class Swordsman extends GameUnit {
  private var state: String = "idle"

  def attack(): Unit = {
    this.state = "attacking"
  }

  override def toString: String = "Swordsman" + state + getPosition

  override def reset: Unit = this.state = "idle"

}

class General extends GameUnit {
  private var state: String = "idle"

  def attack(): Unit = {
    this.state = "MOral Boost"
  }

  override def toString: String = "Swordsman" + state + getPosition

  override def clone(): GameUnit = throw new CloneNotSupportedException("General are unique")
  override def reset: Unit       = throw new UnsupportedOperationException("Reset not supported")
}

val s1 = new Swordsman()
s1.move(new Point3D(-10, 0, 0), 20)
s1.attack()
s1

val s2 = s1.clone()
s2
