package com.suanki.paramParsing

abstract class ParamParser extends App {
  println("==" * 30)
  // ~scalaDev/runMain com.suanki.paramParsing.Main -d "2022-12-30" -i 10 -a typeA
  val _pram: Arguments = parse(args)
  def param: Arguments = _pram

  def parse(args: Seq[String]): Arguments = {
    parser.parse(args, Arguments()) match {
      case Some(arguments) => arguments
      case None            => throw new ExceptionInInitializerError("argument is not correct")
    }
  }

  private lazy val parser = new scopt.OptionParser[Arguments]("Parsing application") {

    opt[String]('d', "date").required().valueName("").action((value, args) => args.copy(date = value))

    opt[Int]('i', "id").required().valueName("").action((value, args) => args.copy(id = value))

    opt[String]('a', "apiName").valueName("").action((value, args) => args.copy(sourceName = value))

    opt[String]('t', "tableName").valueName("").action((value, args) => args.copy(tableName = value))

  }

}
