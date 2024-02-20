package com.suanki

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

abstract class UnitSpec extends AnyFlatSpec with Matchers {

  def getSparkSession: SparkSession = SparkSession
    .builder()
    .master("local[2]")
    .appName("testCases")
    .getOrCreate()
}
