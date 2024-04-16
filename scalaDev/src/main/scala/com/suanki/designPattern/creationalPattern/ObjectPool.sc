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
  def draw(): Unit
  var _location: Point2D                     = _
  def location: Point2D                      = _location
  def location_=(newLocation: Point2D): Unit = _location = newLocation

}

class BitMap extends Image {
  private var name: String = "default"

  def apply(name: String): Unit = this.name = name

  override def draw(): Unit = println(s"draw ${name} @ ${location}")

  override def reset(): Unit = {
    location = null
    println("Bitmap is reset")
  }

  def apply(): BitMap = new BitMap
}

import scala.collection.mutable

class ObjectPool[T <: Poolable](f: T) {

  private val poolSize                     = 3
  private val objectPool: mutable.Queue[T] = mutable.Queue.fill(poolSize)(f)

  // give object from pool
  def get(): T = {
    synchronized {
      try {
        println(s"inside deque get ${objectPool.size}")

        objectPool.dequeue()
      } catch {
        case e: InterruptedException =>
          println("get was interrupted"); null.asInstanceOf[T]

        case e: NoSuchElementException =>
          println("enqueue is empty")
          Thread.sleep(10000)
          null.asInstanceOf[T]
      }
    }
  }

  // add obj back to pool
  def release(obj: T): Unit = {
    synchronized {
      try {
        println(s"inside release get ${objectPool.size}")
        obj.reset()
        objectPool.enqueue(obj)
      } catch {
        case e: InterruptedException   => println("enqueue is interrupted")
        case e: NoSuchElementException => println("enqueue is full")
      }
    }
  }
}

//client

val objectPool: ObjectPool[BitMap] = new ObjectPool[BitMap](new BitMap)
val b1                             = objectPool.get()
b1("test.img")
b1.location = new Point2D(10, 10)
b1.draw()
b1.reset()

val b2 = objectPool.get()
b2("test.img")
b2.location = new Point2D(10, 20)
b2.draw()
b2.reset()

val b3 = objectPool.get()
b3("test.img")
b3.location = new Point2D(10, 30)
b3.draw()
b3.reset()
objectPool.release(b3)

val tsk = List("taskA", "taskB", "taskC")
tsk.foreach(tsk => {
  val t = objectPool.get()
  t(tsk)
  t.location = new Point2D(10, 20)
  t.draw()
  t.reset()
  objectPool.release(t)

})
//Design consideration

//apache commons dbcp library is used for database connecitno pooling,
// Clas sorg.apache.commons.dbcp.BasicDataSource in dbcp pacakge is an example of
//object pool pattern which pools database connecitons.
//this pool is commonly created and exposed via jdni or as a spring bean in applicaiton
