package com.suanki.dev.appliedOne

trait LogHelper {

  lazy val log: String => Unit = msg => println(s"${Console.GREEN} $msg ${Console.RESET}")
}

case class Parameter(srcName: String, date: String, runId: String)
class ParamParser(args: Seq[String]) {
  def param: Parameter = _param
  private[this] lazy val _param = Parameter(
    date = parsedMap.getOrElse("date", ""),
    runId = parsedMap.getOrElse("run", ""),
    srcName = parsedMap.getOrElse("srcName", "")
  )
  private[this] val parsedMap = parse(Map(), args.toList)
  def parse(map: Map[String, String], args: Seq[String]): Map[String, String] = {
    args match {
      case "--date" :: value :: rest =>
        parse(map ++ Map("cob" -> value), rest)
      case "--run" :: value :: rest =>
        parse(map ++ Map("run" -> value), rest)
      case "--srcName" :: value :: rest =>
        parse(map ++ Map("srcName" -> value), rest)
      case Nil => map
    }
  }

}

class ApiData(ctx: Parameter) {
  def getSourceDf: String = "ApiDF"
}

class TableData(ctx: Parameter) {
  def getSourceDf: String = "TableDF"
}

trait TestUtils {
  val testSources = List("A", "B", "C")
}

class Test extends TestUtils {
  private[this] var _ctx        = Parameter("", "", "")
  def ctx: Parameter            = _ctx
  def ctx_=(x: Parameter): Unit = _ctx = x

  def fetchSourceDf: String = {
    if (ctx.srcName == "A") {
      new ApiData(ctx).getSourceDf
    } else {
      new TableData(ctx).getSourceDf
    }
  }
}

object InitTest extends LogHelper {

  def prepareArgs(args: List[String], element: String): List[String] = {
    args ::: List("--srcName", element)
  }

  def main(args: Array[String]): Unit = {
    println("=" * 50)
    val test = new Test

    test.testSources foreach { elem =>
      log(s"running test for $elem")
      val ctx = new ParamParser(prepareArgs(args.toList, elem))
      test.ctx = ctx.param
      val sourceDF = test.fetchSourceDf
      log(s"test completed for $sourceDF")
    }

    println("=" * 50)

  }
}
