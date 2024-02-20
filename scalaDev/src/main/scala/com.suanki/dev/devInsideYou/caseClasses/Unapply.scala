package com.suanki.tutorials.devInsideYou.caseClasses

object Unapply {

  def code: Unit = {

    val implicitFile: String =
      "C:/Users/sujee/OneDrive/Documents/bigdata_and_hadoop/scala/spark-sbt-dev/src/main/scala/com/suanki/tutorials/devInsideYou/caseClasses/Main.scala"
    // or
    val explicitFile: String = File(
      location =
        "C:/Users/sujee/OneDrive/Documents/bigdata_and_hadoop/scala/spark-sbt-dev/src/main/scala/com/suanki/tutorials/devInsideYou/caseClasses",
      name = "Main",
      extension = ".scala"
    )

    println(implicitFile)
    println(explicitFile)
    println(implicitFile == explicitFile)

    implicitFile match {
      case FileIsMoreThan20Char() => println("yes")
      case File(l, n, e) =>
        println(
          s"""location: $l
             |name: $n
             |extension: $e
             |""".stripMargin
        )
    }
  }
}
