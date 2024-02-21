package com.suanki

import com.suanki.sparkUtils.SparkUtils

object Main {

  def main(args: Array[String]): Unit = {

    println("=" * 50)
    println("Starting...")
    val spark: SparkUtils = new SparkUtils()

    // join
    val joins = new Joins(spark.getSparkSession())

    // inner join
    joins.innerJoin()

    println("End....")
    println("=" * 50)
  }
}
