package com.suanki.dev.paramParsing
case class Arguments(
    one: String = "",
    two: Int = 1
)

object Parser extends App {

  def code(param: Arguments): Unit = {

    println("=" * 50)
    println(param.one)
    println(param.two)
    println("=" * 50)
  }

  def param: Arguments = parser.parse(args, Arguments()) match {
    case Some(arguments) => arguments
    case None            => throw new ExceptionInInitializerError("argument is not correct")
  }

  val parser = new scopt.OptionParser[Arguments]("Parsing application") {

    opt[String]('o', "one").required().valueName("").action((value, args) => args.copy(one = value))

    opt[Int]('t', "two").required().valueName("").action((value, args) => args.copy(two = value))

  }

  code(param)

}
