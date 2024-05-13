package com.suanki.dev.typeSystem.genericType

import com.suanki.logger.StdOut._
//contravariant
trait Sink[-T] {
  def send(item: T): String
}

object AppleSink extends Sink[Apple] {
  override def send(item: Apple): String = s"Coring and Eating ${item.name}"
}

object OrangeSink extends Sink[Orange] {
  override def send(item: Orange): String = s"Juicing and drinking ${item.name}"
}

object FruitSink extends Sink[Fruit] {
  override def send(item: Fruit): String = s"Eating a healthy ${item.name}"
}

object FoodSink extends Sink[Food] {
  override def send(item: Food): String = s"Eating a healthy ${item.name}"
}

object AnySink extends Sink[Any] {
  override def send(item: Any): String = s"Sending ${item.toString}"
}

class Contravariant {

  // handler
  def sinkAnApple(sink: Sink[Apple]): String = {
    sink.send(Apple("fuji"))
  }

  lazy val contravariant: Unit = {

    val fuji = Apple("fuji apple")
    AppleSink.send(fuji).show
    sinkAnApple(AppleSink)
//    sinkAnApple(  OrangeSink) // won't work, this is not invariant b/c Sink[Orange] is not subtype or super type of Sink[Fruit]

    sinkAnApple(FruitSink) // this is invariant, Sink[Fruit] is super type of Sink[Apple]
    // meaning Fruit >: Apple , lowerbound, so replace T with -T
    sinkAnApple(FoodSink)

  }
}
