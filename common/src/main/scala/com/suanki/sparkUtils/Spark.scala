package com.suanki.sparkUtils

import org.apache.spark.sql.SparkSession

trait Spark {

  implicit lazy val spark: SparkSession = createSparkSession
  implicit lazy val appName: String     = "sourcingApp"

  def createSparkSession: SparkSession =
    SparkSession
      .builder()
      .master("local[4]")
      .appName(appName)
      .getOrCreate()

}
