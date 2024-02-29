package com.suanki.dev.devInsideYou.ExceptionsInScala

import com.suanki.dev.devInsideYou.ExceptionsInScala.Helpers._

object ExceptionPartOne {

  def main(args: Array[String]): Unit = {
    println("=" * 50)

    println(
      """
        |                                     Throwable
        |                 Exception                                   Error (No need to handle then)
        |
        |
        |""".stripMargin
    )

    code(args)
    println("=" * 50)
  }

  def code(args: Array[String]): Unit = {

    val file = new File(
      location = "./src/main/scala",
      name = "Main",
      extention = ".scala",
      content = "Hello World!"
    )

    var diskUsage = 10 // bytes
    def happyPath(size: Int): Unit = {
      showGreen("Life is good!")
      diskUsage += size
    }

    def safeCall(): Unit = {
      try {
        val size = file.convenientWrite()
        happyPath(size)
      } catch {
        case Write.Warning(message)   => showYellow(message);
        case e @ Write.Error(message) => showYellow(message); throw e
        case e: Exception             => showRed(e.toString);
      } finally println("finally")
    }

    safeCall()

//
//     optionalSize match {
//       case Some(size) => happyPath(size)
//       case Write.Success(size) => happyPath(size)
//       case Write.Warning(message) => showYellow(message)
//       case Write.Error(message) => showRed(message)
//       case _ => showRed("something wrong!")
//     }

    println(s"Disk usage: $diskUsage bytes. ")

  }
}

class File(
    location: String,
    name: String,
    content: String,
    extention: String
) {

//  def write(): Option[Int]= {
  def write(): Write.Result = {
    println(s"writing '$content' to '$location'...")
    Thread.sleep(1000)
//    Some(content.size)
//    None   // java.util.NoSuchElementException: None.get    in caller
    Write.Warning("disk cleanup needed!")
  }

  def safeWrite(): Write.Result = {
    println(s"writing '$content' to '$location'...")
    Thread.sleep(1000)
    Write.Warning("disk cleanup needed!")
  }

  def convenientWrite(): Int = {
    throw Write.Warning("not enough  space disk cleanup needed!")
//    throw  Write.Error("not enough  space disk cleanup needed!")
    println(s"writing '$content' to '$location'...")
    Thread.sleep(1000)
    12
  }

}

object Write {
  sealed trait Result
  final case class Success(size: Int) extends Result
  final case class Warning(message: String) extends Throwable with Result {
    override def toString: String = s"$message"
  }
  final case class Error(message: String) extends Throwable with Result
}
