package com.suanki.fileRecon

import org.apache.spark.sql.{DataFrame, SparkSession}

final case class JobParams(
    runType: String,
    date: String,
    tableName: String,
    location: String
)

trait Readers {

  val tableQuery: String = ""
  val tableName: String  = ""

  def buildWhere(tableName: String, spark: SparkSession): String

  def buildSelect: String = {
    s"select * from $tableName"
  }

  def buildQuery(tableName: String, spark: SparkSession): String = {
    s"${buildSelect} where ${buildWhere(tableName, spark)}"
  }

  def createDataFrame: DataFrame

  def prepareWhere(
      partitionCols: List[String],
      param: JobParams
  ): String = {
    var where = " 1=1 "
    partitionCols.foreach(cols => {
      cols match {
        case "date" => where = s"$where AND date=${param.date}"
        case "location" =>
          where = s"$where AND location='${param.location}'"
      }
    })
    where
  }

  def getDataFrame: DataFrame = {
    createDataFrame
  }

}

class StgTableReaders(
    param: JobParams,
    utils: FileUtils,
    spark: SparkSession
) extends Readers {

  override val tableName: String =
    utils.getTableName(param.tableName, this)
  override val tableQuery: String = buildQuery(tableName, spark)

  override def createDataFrame: DataFrame = {
    println(s"table Query are ------------------------->: $tableQuery")
    spark.range(5).toDF(tableName)
  }

  override def buildWhere(
      tableName: String,
      spark: SparkSession
  ): String = {
    super.prepareWhere(utils.fetchPartitionCols(tableName, spark), param)
  }

}

class FinalTableReaders(
    param: JobParams,
    utils: FileUtils,
    spark: SparkSession
) extends Readers {

  override val tableName: String =
    utils.getTableName(param.tableName, this)
  override val tableQuery: String = buildQuery(tableName, spark)

  override def createDataFrame: DataFrame = {
    println(s"table Query are ------------------------->: $tableQuery")
    spark.range(5).toDF(tableName)
  }

  override def buildWhere(
      tableNam: String,
      spark: SparkSession
  ): String = {
    super.prepareWhere(utils.fetchPartitionCols(tableName, spark), param)
  }

}

object TableReader {
  def apply(readerType: Readers): DataFrame = {
    readerType.getDataFrame
  }
}

object ShowTime {

  def main(args: Array[String]): Unit = {
    val utils: FileUtils      = new FileUtils
    val session: SparkSession = utils.getSparkSession()
    val param: JobParams      = JobParams("1", "2023-12-30", "India", "APAC")

    val stgTableReader: StgTableReaders =
      new StgTableReaders(param, utils, session)
    val finalTableReaders: FinalTableReaders =
      new FinalTableReaders(param, utils, session)

    val stgDF   = TableReader(stgTableReader)
    val finalDf = TableReader(finalTableReaders)

    stgDF.show(5, true)
    finalDf.show(5, true)

  }

}
