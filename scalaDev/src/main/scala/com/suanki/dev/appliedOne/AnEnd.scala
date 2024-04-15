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

  def populateTable(tableName: String, action: String)(fn: String => Boolean): AnEnd.type = {
    val isPresent = !fn(tableName)
    action match {
      case "stgTable" if isPresent   => println("writing stg df")
      case "finalTable" if isPresent => println("writing final df")
      case "transtable" if isPresent => println("writing trans df")
      case _                         => println(s"skipping populate table for ${tableName}")
    }
    this
  }

  def pupulate[A](table: String, tableType: A)(b: () => Boolean): A = {
    tableType match {
      case _ => tableType
    }
  }

  def testing: Unit = {

    println("==>inside testing table")
    val stgTable: String   = "table_stg"
    val finalTable: String = ""

    pupulate(stgTable, "someDF")(() => stgTable.isEmpty)

    populateTable(stgTable, "stgTable")(isValue => stgTable.isEmpty)
      .populateTable(stgTable, "finalTable")(isValue => finalTable.isEmpty)
      .populateTable(stgTable, "transTable")(isValue => finalTable.isEmpty)

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

    writingOurOwnLoop

    testing

  }
}
