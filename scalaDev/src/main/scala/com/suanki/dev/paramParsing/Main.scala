package com.suanki.dev.paramParsing

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.SparkSession
case class Arguments(
    date: String = "",
    id: Int = 1,
    apiName: String = "",
    tableName: String = ""
)

abstract class Parser extends App {
//~scalaDev/runMain com.suanki.dev.paramParsing.Main -d "2022-12-30" -i 10 -a typeA
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

    opt[String]('a', "apiName").valueName("").action((value, args) => args.copy(apiName = value))

    opt[String]('t', "tableName").valueName("").action((value, args) => args.copy(tableName = value))

  }

}

trait Spark {
  lazy val spark: SparkSession = SparkSession
    .builder()
    .appName("parse demo")
    .master("local[2]")
    .getOrCreate()

}

trait Api {

  def callApi(apiName: String): Unit = {
    println(s"calling $apiName")
  }

}
class ApiApp extends Api with Spark {}

class TableApp extends Spark {}

object StagingApi extends Parser with Api {
  println("=" * 50)
  val parserConf: Config = ConfigFactory.load("application.conf")
  val apiObject          = parserConf.getConfig("some.api")

  param.apiName match {
    case apiName @ "TypeA" => {
      callApi(apiName)
    }
    case apiName @ "TypeA1" => {
      callApi(apiName)
    }
    case apiName @ "TypeB1" => {
      callApi(apiName)
    }
  }

  println("=" * 50)
}

object StagingTable extends Parser with Spark {
  println("=" * 50)
  val parserConf: Config = ConfigFactory.load("application.conf")
  val tableObject        = parserConf.getConfig("some.table")

  println(tableObject.getString("typeR1"))

  println("=" * 50)
}
