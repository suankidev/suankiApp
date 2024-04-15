package com.suanki.sparkUtils

import org.apache.log4j.Logger
import org.apache.spark.sql.{DataFrame, SparkSession}

object AppLogger extends Serializable {

  def getAppLogger(loggerName: Any): org.apache.log4j.Logger = {
    lazy val appLogger =
      org.apache.log4j.Logger.getLogger(loggerName.getClass)
    appLogger
  }

  def getInfoMsg(msg: Any, appLogger: Logger): Unit =
    appLogger.info(s"${Console.GREEN}${msg}${Console.RESET}")

  def getWarnMsg(msg: Any, appLogger: Logger): Unit =
    appLogger.info(s"${Console.GREEN}${msg}${Console.RESET}")

}
class CommonUtils(spark: SparkSession) {

  def readDF(
      path: String,
      filetype: String = "parquet",
      opt: Map[String, String],
      isSchema: Boolean = false,
      dfSchema: String = "a b c"
  ): DataFrame = {

    println(s"path: $path")
    if (isSchema) {
      spark.read
        .format(filetype)
        .options(opt)
        .schema(dfSchema)
        .load(path)
    } else
      spark.read.format(filetype).options(opt).load(path)

  }

  def readDFWithFileName(file_name: String) = {

    spark.read
      .format("json")
      .options(Map("inferSchema" -> "true", "header" -> "true"))
      .load(
        raw"C:\Users\sujee\Desktop\spark-sbt-dev\src\main\resources\data\${file_name}.json"
      )
  }

}
