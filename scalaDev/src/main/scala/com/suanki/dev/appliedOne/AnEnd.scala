package com.suanki.dev.appliedOne
import java.io.File
import scala.io.Source
object AnEnd {

  // function arity

  def writingOurOwnLoop: Unit = {

    @scala.annotation.tailrec
    def numberLoop(pred: () => Boolean)(body: () => Unit): Unit = {
      if (pred()) {
        body()
        numberLoop(pred)(body)
      }
    }

    var x = 0
    numberLoop(() => x < 5) { () =>
      println(x * x)
      x += 1
    }

  }

  // using the content of the files?

  def fileContainsQuestion(file: File): Boolean = {
    val source = Source.fromFile(file)

    try {
      source
        .getLines()
        .toSeq
        .headOption
        .map { line =>
          line.trim.endsWith("?")
        }
        .getOrElse(false)
    } finally source.close()
  }

  def emphasizeFileContains(file: File): String = {
    val source = Source.fromFile(file)

    try {
      source
        .getLines()
        .toSeq
        .headOption
        .map { line =>
          line.trim.toUpperCase()
        }
        .getOrElse("")
    } finally source.close()
  }

  def withFileContent[A](file: File, fn: String => A, default: A): A = {
    val source = Source.fromFile(file)

    try {
      source
        .getLines()
        .toSeq
        .headOption
        .map { line =>
          fn(line)
        }
        .getOrElse(default)
    } finally source.close()
  }

  def code: Unit = {

    val path =
      "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\suankiApp\\sparkWithScala\\src\\main\\scala\\com.suanki\\Main.scala"
    println(fileContainsQuestion(new File(path)))
    println(emphasizeFileContains(new File(path)))

    // functional way of writting
    withFileContent(
      new File(path),
      line => line.trim.toUpperCase(),
      ""
    )

    val x: (Int, Int, Int) => Int = (a, b, c) => a + b + c
    val y                         = x.curried

    println(y(10)(20)(30))

    def populateTables[A](tableName: String)(fn: String => A): Unit = {

      val df = fn(tableName)

    }

    populateTables("stgTable") { tableName =>
      println(s"reading table $tableName")
    }

    populateTables("finalTable") { tableName =>
      println(s"reading table $tableName")
    }

    populateTables("stgTransformer") { tableName =>
      println(s"reading table $tableName")
    }

    writingOurOwnLoop

  }
}
