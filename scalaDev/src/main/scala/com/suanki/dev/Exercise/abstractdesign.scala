package com.suanki.dev.Exercise

import org.apache.spark.sql.{DataFrame, SparkSession}

abstract class FileRecon {

  def createStgTableDataFrame: DataFrame
  def createFinalTableDataFrame: DataFrame
  def createSourceFileDataFrame: DataFrame

}

object ResultGenerator {

  def TestRunner(
      stgDF: DataFrame,
      finalDF: DataFrame,
      fileDf: DataFrame
  ): Unit = {

    println("Running test runner")
    fileDf.show()

    println("End of test runner")
  }
}

class FileTestRunner extends FileRecon {

  protected var stgTableQuery: String   = ""
  protected var finalTableQuery: String = ""
  protected var spark: SparkSession = SparkSession
    .builder()
    .appName("local")
    .config("spark.master", "local[2]")
    .getOrCreate()
  var stgTableDF: DataFrame   = spark.emptyDataFrame
  var finalTableDF: DataFrame = spark.emptyDataFrame
  var sourceFileDF: DataFrame = spark.emptyDataFrame

  override def createStgTableDataFrame: DataFrame   = stgTableDF
  override def createFinalTableDataFrame: DataFrame = finalTableDF
  override def createSourceFileDataFrame: DataFrame = sourceFileDF

}
class TableReader extends FileTestRunner {

  override def createStgTableDataFrame = {
    stgTableDF = spark.range(10).toDF("stgdf")
    stgTableDF
  }

}

class FileReader extends FileTestRunner {

  override def createSourceFileDataFrame: DataFrame = {
    sourceFileDF = spark.range(15).toDF("sourcefile")
    sourceFileDF
  }

}

object InitFileBasedCheck extends App {

  val fileReader  = new FileReader
  val tableReader = new TableReader
  fileReader.createSourceFileDataFrame
  fileReader.sourceFileDF.show()
  ResultGenerator.TestRunner(
    tableReader.stgTableDF,
    tableReader.finalTableDF,
    fileReader.sourceFileDF
  )

}
