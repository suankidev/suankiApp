import javafx.geometry.Point2D

//if cost of creating an instance of a class is high and we need a large number of objects
//of this class for short duration, then we can use an object pool

// we either pre-create object of the class or collect unused instances in an in memory cache
// when code needs an object of this

//reset internal stat of object before giving it to new process
trait Poolable {
  def reset(): Unit
}

trait Image extends Poolable {
  def draw():Unit
  var _location: Point2D = _
  def location:Point2D=_location
  def location_=(newLocation:Point2D): Unit =_location = newLocation

}


class BitMap extends Image{
  private var name:String =  _
  def apply(name:String):BitMap ={
    this.name = name
    new BitMap
  }
  override def draw(): Unit = println(s"draw ${name} @ ${location}")

  override def reset(): Unit = {
    location = null
    println("Bitmap is reset")
  }
}

import scala.collection.mutable

class ObjectPool[T <: Poolable]{

  private val poolSize = 10
  private val objectPool: mutable.Queue[T] = mutable.Queue.fill(poolSize)(new T())

  //give object from pool
  def get():T={
    synchronized{
      try
        {
          objectPool.dequeue()
        }catch {
        case e:InterruptedException => println("get was interrupted")
          null.asInstanceOf[T]
      }
    }
  }

  //add obj back to pool
  def release(obj:T):Unit={
     synchronized{
       try {
         obj.reset()
         objectPool.enqueue(obj)
       }catch {
         case e:InterruptedException => println("enqueue is interrupted")
       }
       }
  }
}



//client

val objectPool:ObjectPool[BitMap] = new ObjectPool[BitMap]()

val b1 = objectPool.get()("img.jpeg")
b1.location = new Point2D(10,10)

val b2 = objectPool.get()("tst.jpeg")
b2.location = new Point2D(40,30)




