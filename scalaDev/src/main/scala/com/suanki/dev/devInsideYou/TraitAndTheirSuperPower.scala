package com.suanki.dev.devInsideYou

import java.io.File

object TraitAndTheirSuperPower {

  def main(args: Array[String]) = {

    println("=" * 50)

    trait Animal

    trait Pet {
      def allowedToSleepInBed: Boolean

      def disallowdToSleepInBed: Boolean =
        !allowedToSleepInBed // rich interfaces/thin interfaces functionality
    }

    class Turtle extends Animal with Pet {

      override def allowedToSleepInBed: Boolean = false
    }

    class Cat extends Animal with Pet {
      override def allowedToSleepInBed: Boolean = true
    }

    def show(pet: Pet) = {
      println(pet.disallowdToSleepInBed)
    }

    //    show(new Cat)
    //    show(new Turtle)

    trait Timestamp {
      val creationTime: String = {
        val formatter =
          java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")

        java.time.LocalDateTime.now.format(formatter)
      }
    }

    class FileWithTimeStamp(path: String) extends File(path) with Timestamp

    val path =
      raw"C:\Users\sujee\OneDrive\Documents\bigdata_and_hadoop\scala\spark-sbt-dev\src\main\resources\data\sample_text.txt"
    val file = new FileWithTimeStamp(path)
    //      println(file.creationTime)
    //      println(file.exists())
    //      println(file.getName)

    code(args)
    println("=" * 50)
  }

  def code(Args: Array[String]): Unit = {
    object Base {
      abstract class Car {
        def model: String

        def topSpeedInKmPerHour: Int

        def topAccelerationInRpm: Int

        override def toString: String = {
          val brand = getClass.getSimpleName
          s"$brand $model $topSpeedInKmPerHour $topAccelerationInRpm"
        }
      }

    }

    object Core {
      class OrdinaryCar(override val model: String) extends Base.Car {
        override def topSpeedInKmPerHour: Int = 220

        override def topAccelerationInRpm: Int = 8000
      }

      class SportsCar(override val model: String) extends Base.Car {
        override def topSpeedInKmPerHour: Int = 300

        override def topAccelerationInRpm: Int = 11000
      }
    }

    //
    object Modification {
      trait Spoiler extends Base.Car {
        abstract override def topSpeedInKmPerHour: Int =
          super.topSpeedInKmPerHour * 2

        abstract override def topAccelerationInRpm: Int =
          super.topAccelerationInRpm * 2
      }

      trait EngineControlUnit extends Core.OrdinaryCar {
        override def topSpeedInKmPerHour: Int =
          (super.topSpeedInKmPerHour * 1.5).toInt

      }

      trait TurboCharger extends Core.OrdinaryCar {
        override def topAccelerationInRpm: Int =
          (super.topAccelerationInRpm * 1.5).toInt
      }
    }

    final class Lamborghini(override val model: String) extends Core.SportsCar(model) with Modification.Spoiler

    final class BMW(override val model: String)
        extends Core.OrdinaryCar(model)
        with Modification.Spoiler
        with Modification.TurboCharger
        with Modification.EngineControlUnit

    println(new Lamborghini("Mercielus"))
    println(new BMW("M3-GTR"))

  }
}
