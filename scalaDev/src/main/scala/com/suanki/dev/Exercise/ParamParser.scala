package com.suanki.dev.Exercise
case class ParamConfig(srcName: String, run_id: Int)

abstract class ParamParser extends App {

  println("=" * 50)
  def param: ParamConfig = _param
  val options            = parseArg(Map(), args.toList)

  lazy val _param = {
    ParamConfig(
      srcName = options.getOrElse("arg1", "defaultSrc"),
      run_id = options.getOrElse("arg2", "1").toInt
    )
  }

  val usage = """
  Usage: pattern matching [--arg1 num] [--arg2 num] filename
"""

  if (args.length == 0) println(usage)

  def parseArg(map: Map[String, String], list: List[String]): Map[String, String] = {
    list match {
      case Nil => map
      case "--arg1" :: value :: tail =>
        parseArg(map ++ Map("arg1" -> value), tail)
      case "--arg2" :: value :: tail =>
        parseArg(map ++ Map("arg2" -> value), tail)
      case string :: Nil =>
        parseArg(map ++ Map("filename" -> string), list.tail)
      case unknown :: _ =>
        println("Unknown option " + unknown)
        sys.exit(1)
    }
  }

}

object Demo extends ParamParser {

  println(options)
  println(param)

  param.srcName match {
    case "txnAbc" => println("inside test class")
    case _        => println("default case")
  }

  println("=" * 50)

}
