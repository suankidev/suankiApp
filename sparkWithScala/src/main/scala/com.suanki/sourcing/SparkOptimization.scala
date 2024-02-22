package com.suanki.sourcing

import com.suanki.sparkUtils.{AppLogger, CommonUtils}
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession

class SparkOptimization(spark: SparkSession, utils: CommonUtils) {
  val appLogger: Logger = AppLogger.getAppLogger(this)

  def narrowTransformation(): Unit = {

    AppLogger.getInfoMsg("Running narrowTransformation() function", appLogger)

    val df = {
      val path1 =
        "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\scala\\spark-sbt-dev\\src\\main\\resources\\data\\retail-data\\2010-12-01.csv"
      val path2 =
        "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\scala\\spark-sbt-dev\\src\\main\\resources\\data\\retail-data\\2010-12-02.csv"
      val path3 =
        "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\scala\\spark-sbt-dev\\src\\main\\resources\\data\\retail-data\\2010-12-03.csv"
      val path4 =
        "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\scala\\spark-sbt-dev\\src\\main\\resources\\data\\retail-data\\2010-12-05.csv"
      val path5 =
        "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\scala\\spark-sbt-dev\\src\\main\\resources\\data\\retail-data\\2010-12-06.csv"
      val path6 =
        "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\scala\\spark-sbt-dev\\src\\main\\resources\\data\\retail-data\\2010-12-07.csv"
      spark.read
        .format("csv")
        .option("header", true)
        .load(path1, path2, path3, path4, path5, path6)
    }

    // appLogger.info(df.schema)

    AppLogger.getInfoMsg(
      s""" spark sql shuffle partitions:spark.sql.shuffle.partitions -->
       ${spark.conf.get("spark.sql.shuffle.partitions")}""",
      appLogger
    )

    AppLogger.getInfoMsg(
      s""" spark default parallelism memory partition can be created based on this Core and other algo:spark.sparkContext.defaultParallelism-->
         |${spark.sparkContext.defaultParallelism}""".stripMargin,
      appLogger
    )

    AppLogger.getInfoMsg(
      s"""df memory partitions:df.rdd.getNumPartitions-->${df.rdd.getNumPartitions}""",
      appLogger
    )
    AppLogger.getInfoMsg(s"""df storage level-->${df.storageLevel}""", appLogger)

    //
    //    spark.conf.set("spark.default.parallelism",200)
    //
    //    appLogger.info(s" spark default parallelism valid for rdd--> ${spark.conf.get("spark.default.parallelism")}")

    AppLogger.getInfoMsg(
      s"""spak adaptive -->
         |${spark.conf.get("spark.sql.adaptive.enabled")}
         |${spark.conf.get("spark.sql.adaptive.enabled")}
         |${spark.conf.get("spark.sql.adaptive.enabled")}
         |""".stripMargin,
      appLogger
    )

    spark.conf.set("spark.sql.adaptive.enabled", true)
    spark.conf.set("spark.sql.adaptive.coalescePartitions", true)
    spark.conf.set("spark.databricks.io.cache.enabled", true)

    val dfData = Seq(
      ("Sujeet", 26, "Gaduation"),
      ("Rama", 23, "Post Graduation"),
      ("Sujeet", 26, "Gaduation"),
      ("Rama", 23, "Post Graduation"),
      ("Sujeet", 26, "Gaduation"),
      ("Rama", 23, "Post Graduation"),
      ("Sujeet", 26, "Gaduation"),
      ("Rama", 23, "Post Graduation"),
      ("Sujeet", 26, "Gaduation"),
      ("Rama", 23, "Post Graduation"),
      ("Sujeet", 26, "Gaduation"),
      ("Rama", 23, "Post Graduation")
    )
    val dfRdd = spark.sparkContext.parallelize(
      dfData
    ) // .toDF("name string, age int,education string")

    AppLogger.getInfoMsg(
      s"df Rdd partitions ${dfRdd.getNumPartitions}",
      appLogger
    ) // by default it will be no-of cores in the system

    AppLogger.getInfoMsg("End of  narrowTransformation() function ! ", appLogger)

  }

  def wideTransformation(): Unit = {}

}
