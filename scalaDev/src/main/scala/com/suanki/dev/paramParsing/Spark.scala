package com.suanki.dev.paramParsing

import org.apache.spark.sql.SparkSession

trait Spark {
  lazy val spark: SparkSession = SparkSession
    .builder()
    .appName("parse demo")
    .master("local[2]")
    .getOrCreate()

}
