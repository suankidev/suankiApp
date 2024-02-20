package com.suanki.tutorials.devInsideYou.traitPower

object Main {

  /** what trait can do
    *   1. add functionality 2. modify functionality 3. intercept functionality
    */
  def main(args: Array[String]): Unit = {
    println("=" * 30)
    // trait mixin
    traitMixIN()

    // stackable modification pattern
    //
    // 1.                        base trait
    //              |                               |
    // 2. core(classes)                            modification(trait

    println("=" * 30)
  }

  def traitMixIN(): Unit = {

    trait TimeStamp {
      val creationTime: String = {
        val formatter = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")
        java.time.LocalDateTime.now.format(formatter)
      }
    }

    class FileWithTimestamp(path: String) extends java.io.File(path) with TimeStamp

    val path =
      "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\scala\\spark-sbt-dev\\src\\main\\scala\\com\\suanki\\tutorials\\devInsideYou\\traitPower\\Main.scala"

    val file: FileWithTimestamp = new FileWithTimestamp(
      path
    )

    println(file.getName)
    println(file.creationTime)

    def showCreationTime(time: TimeStamp): Unit = {
      println(time.creationTime)
    }

    // see how this works//b/c file is FileWithTimestamp
    showCreationTime(file)

    // you can't extend over functionality String since this is final

    // illegal inheritance from final class String
//    val hello = new String("Test String") with TimeStamp
//
//    println(hello.creationTime)
//    println(hello)

  }

}
