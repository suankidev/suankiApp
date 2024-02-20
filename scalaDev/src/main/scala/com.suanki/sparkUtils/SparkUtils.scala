package com.suanki.sparkUtils

import org.apache.spark.sql.SparkSession

class SparkUtils {

  def getSparkSession(): SparkSession = {

    val spark = SparkSession
      .builder()
      .appName("F1CDLMDL_TEST")
      .master("local[4]")
      .getOrCreate()

    val sc = spark.sparkContext
    spark.sparkContext.setLogLevel("INFO")
    spark.conf.set("spark.sql.adaptive.enabled", false)
    spark.conf.set("spark.sql.adaptive.coalescePartitions.enabled", false)
    // spark.conf.set("spark.sql.warehouse.dir","C:\\Users\\sujee\\Desktop\\spark_output\\warehouse_dir\\")

    spark
  }

}
