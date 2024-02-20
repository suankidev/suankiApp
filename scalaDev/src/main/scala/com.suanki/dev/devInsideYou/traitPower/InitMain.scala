package com.suanki.dev.devInsideYou.traitPower

object InitMain extends App {

  // stackable modification pattern
  //
  // 1.                        base trait
  //              |                               |
  // 2. core(classes)                            modification(trait
  final class Lamborghini(override val model: String) extends Core.SportCar(model) with Modification.Spoiler

  final class BMW(override val model: String)
      extends Core.OrdinaryCar(model)
      with Modification.Spoiler
      with Modification.EngineControlUnit
      with Modification.TurboCharging

  println("=" * 20)
  println(new Lamborghini("Murcielago"))
  println(new BMW("M3-GTR"))
  println("=" * 20)

}
