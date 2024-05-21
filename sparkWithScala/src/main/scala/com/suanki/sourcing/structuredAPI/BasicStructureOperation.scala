package com.suanki.sourcing.structuredAPI

import com.suanki.sparkUtils.SparkUtils
import org.apache.spark.sql
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object BasicStructureOperation {
  val flightPath: String =
    raw"src/main/resources/data/flight_data/2015-summary.csv"
  val retailPath: String =
    raw"src/main/resources/data/retail-data/2010-12-01.csv"

  def main(args: Array[String]): Unit = {

    println("=" * 30)
    println()

    val retailSchema =
      StructType(
        List(
          StructField("InvoiceNo", IntegerType, nullable = true),
          StructField("StockCode", StringType, nullable = true),
          StructField("Description", StringType, nullable = true),
          StructField("Quantity", IntegerType, nullable = true),
          StructField("InvoiceDate", TimestampType, nullable = true),
          StructField("UnitPrice", DoubleType, nullable = true),
          StructField("CustomerID", StringType, nullable = true),
          StructField("Country", StringType, nullable = true)
        )
      )

//    val util: SparkUtils = new SparkUtils()
//    val spark: SparkSession = util.getSparkSession()
//    def flightDf = spark.read.format("csv").option("header", "true").load(flightPath)
//    def retailDf = spark.read.format("csv").option("header", "true")
//      .schema(retailSchema).load(retailPath)

    //    dataSetsTypeSafe(readFightDF(util, spark),spark)
    //    SchemaOnReadAndSchema(spark)
    //    creatingRowByHand(spark)
    //    convertingToSparkTypesLiterals(spark,flightDf,retailDf)
    //    workingWithBoolean(spark, flightDf, retailDf)
    // dataSources(spark,flightDf, retailDf)
    // Thread.sleep((5000) )

//    readJdbc(spark)

    manipulation()

    println()
    println("=" * 30)
  }

  def manipulation(): Unit = {
//    val testString = "This is test string"
//    println(testString.split(" ").reverse.mkString(" "))
//    println(testString)
//    val testList = List("this","is","another")
//    testList.map(row => row.toUpperCase).foreach(println)
//    testList.flatMap(row => row.toUpperCase).foreach(println)

  }

  def readJdbc(spark: SparkSession): Unit = {
    val url =
      raw"jdbc:oracle:thin:suanki/testpass@//localhost:1521/PDBORCL"
    val driver = raw"oracle.jdbc.driver.OracleDriver"

    // parallel load
    val minMax = (spark.read
      .format("jdbc")
      .option("url", url)
      .option("user", "suanki")
      .option("driver", driver)
      .option("password", "test")
      .option(
        "query",
        s"""select cast(0 as int) as minid
                    , max(cast(product_id as int)) as maxid from products"""
      )
      .load())

    val minMaxCasted =
      minMax.select(col("minid").cast("int"), col("maxid").cast("int"))

    val collectMinMax = minMaxCasted.head()
    val lowerBound    = collectMinMax.getAs[Int]("minid")
    val upperBound    = collectMinMax.getAs[Int]("maxid")

    // println(collectMinMax, lowerBound, upperBound)

    val productDF1 = (spark.read
      .format("jdbc")
      .option("url", url)
      .option("driver", driver)
      .option("user", "suanki")
      .option("password", "test")
      .option("dbtable", "products")
      .load())

    productDF1.rdd.getNumPartitions // 1

    val productDF = (spark.read
      .format("jdbc")
      .option("url", url)
      .option("driver", driver)
      .option("user", "suanki")
      .option("password", "test")
      //      .option("dbtable",
      //        "(select product_id, product_name, description, standard_cost,list_price, CATEGORY_ID from products) as subq")
      .option("dbtable", "products")
      .option("partitionColumn", "product_id")
      .option("lowerBound", lowerBound)
      .option("upperBound", upperBound)
      .option("numPartitions", "8")
      //      .option("fetchsize","1")
      .load())

    productDF.where(col("product_id") === 1).show()
    productDF.rdd.getNumPartitions // 8

    val productDF2 = (spark.read
      .format("jdbc")
      .option("url", url)
      .option("driver", driver)
      .option("user", "suanki")
      .option("password", "test")
      .option(
        "query",
        """select product_id, product_name, description, standard_cost,list_price,
                CATEGORY_ID from products""".stripMargin
      )
      .option("numPartitions", "10")
      .option("fetchsize", "20")
      .load())

    productDF2.where(col("product_id") === 1).show()
    productDF2.rdd.getNumPartitions // 1

  }

  def dataSources(
      spark: SparkSession,
      frame: sql.DataFrame,
      frame1: sql.DataFrame
  ): Unit = {

    val df = spark.read.text(raw"src/main/resources/data/sample_text.txt")

    df.show(10, false)

    // find no of time spark come in file sample_text.txt

    df.withColumn("splitted_col", split(col("value"), " "))
      .withColumn("exploded_cols", explode(col("splitted_col")))
      .where(lower(expr("exploded_cols")) === "spark")
      .select("exploded_cols")
      .show(false)

    val dfRdd =
      spark.sparkContext.textFile(
        raw"C:\Users\sujee\OneDrive\Documents\bigdata_and_hadoop\scala\spark-sbt-dev\src\main\resources\data\sample_text.txt"
      )

    val flight = spark.sparkContext.textFile(
      raw"C:\Users\sujee\OneDrive\Documents\bigdata_and_hadoop\scala\spark-sbt-dev\src\main\resources\data\flight_data\2010-summary.csv"
    )
    val myCollection =
      "Spark The Definitive Guide : Big Data Processing Made Simple"
        .split(" ")

    //
    //    val words = spark.sparkContext.parallelize(myCollection, 2)
    //
    //
    //   words.collect()
    //
    //    words.filter(row => row.startsWith("S")).count() //2
    //

  }

  def workingWithBoolean(
      session: SparkSession,
      flightDf: sql.DataFrame,
      retailDf: sql.DataFrame
  ): Unit = {

    retailDf.printSchema()

    retailDf
      .where(col("invoiceNo").equalTo(536365))
      .select("invoiceno", "description")
    //      .show()

    val priceFilter             = col("unitPrice") > 600
    val DOTCodeFilter           = col("StockCode") === "DOT"
    val stockCodeFilter         = col("stockCode").isin("DOT")
    val descriptionFilter       = col("description").contains("postage")
    val stockCodeFilterMultiple = col("stockCode").isin("85123A", "71053")

    retailDf.where(stockCodeFilter)
    //      .show()

    retailDf.where(priceFilter || descriptionFilter).where(stockCodeFilter)
    //      .show()

    retailDf.select("stockCode").show(5)
    // selecting multiple stockCode

    retailDf.filter(stockCodeFilterMultiple)
    //      .show()

  }

  def convertingToSparkTypesLiterals(
      session: SparkSession,
      flightDF: DataFrame,
      retailDf: DataFrame
  ): Unit = {

    flightDF.select(functions.col("*"), functions.lit(1).alias("one"))

    // adding column
    flightDF.withColumn("whereCounGreateThan30", col("count") > 10)

    // drop
    flightDF.drop(
      "dest_country_name",
      "test"
    ) // no error even column 'test' does not exists

    // distinct and dropDuplicates
    flightDF.select("dest_country_name", "ORIGIN_COUNTRY_NAME").distinct()
    flightDF.dropDuplicates(
      Seq("dest_country_name", "ORIGIN_COUNTRY_NAME")
    )
    flightDF.dropDuplicates("dest_country_name", "ORIGIN_COUNTRY_NAME")

    // taking sample data
    flightDF.sample(false, 0.5, 2)

    // union
    val schema = flightDF.schema
    val newRows = Seq(
      Row("New Country", "Other Country", 5L),
      Row("New Country 2", "Other Country 3", 1L)
    )
    val parallelizedRows = session.sparkContext.parallelize(newRows)
    val newDF            = session.createDataFrame(parallelizedRows, schema)
    flightDF.union(newDF)

    // sort and orderBy
    flightDF.orderBy(col("count").desc)

    // limit
    flightDF.limit(10)

    // repartition vs coalesce
    println(s"SUJEET: ===> ${flightDF.rdd.getNumPartitions}") // 1
    //    session.conf.set("spark.sql.shuffle.partitions",5)
    println(s"SUJEET: ===> ${flightDF.rdd.getNumPartitions}") // 1

    val flightDFPartitioned =
      flightDF.repartition(col("dest_country_name")) // will break in 200

    println(
      s"SUJEET: ===> ${flightDFPartitioned.rdd.getNumPartitions}"
    ) // 200

    val flightDFPartitionedFive =
      flightDF.repartition(5, col("dest_country_name")) // 5
    println(
      s"SUJEET: ===> ${flightDFPartitionedFive.rdd.getNumPartitions}"
    )
    println(
      s"SUJEET: ===> ${flightDFPartitionedFive.coalesce(2).rdd.getNumPartitions}"
    ) // 2

  }

  def creatingRowByHand(sparkSession: SparkSession): Unit = {

    val myRow = org.apache.spark.sql.Row(1, 2, 3, 4, 5)

    //    println(myRow(0))
    //    println(myRow.asInstanceOf[String])
    //    println(myRow.getLong(0))
    val myManualSchema = new StructType(
      Array(
        new StructField("some", StringType, true),
        new StructField("col", StringType, true),
        new StructField("names", LongType, false)
      )
    )

    val myRows = Seq(Row("Hello", null, 1L))

    val myRDD = sparkSession.sparkContext.parallelize(myRows)
    val myDf  = sparkSession.createDataFrame(myRDD, myManualSchema)
    myDf.show()

    import sparkSession.implicits._

    val myDF =
      Seq(("Sujeet", 22, "graduation"), ("Ramesh", 23, "post-graduation"))
        .toDF("col1", "col2", "col3")

    myDF.show()

  }

  def SchemaOnReadAndSchema(sparkSession: SparkSession): Unit = {

    val flightDf = sparkSession.read.format("csv").load(flightPath)
    val retailDf = sparkSession.read.format("csv").load(retailPath)

    //    flightDf.printSchema()
    //    retailDf.printSchema()
    //
    //   println( flightDf.schema)

    // defining a Schema

    val flightSchema = StructType(
      List(
        StructField("DEST_COUNTRY_NAME", StringType, nullable = true),
        StructField("ORIGIN_COUNTRY_NAME", StringType, nullable = true),
        StructField("count", LongType, nullable = true)
      )
    )

    val flightDFWithSchema = sparkSession.read
      .format("csv")
      .option("header", "true")
      .schema(flightSchema)
      .load(flightPath)

    flightDFWithSchema.printSchema()
    println(flightDFWithSchema.schema)

    flightDFWithSchema.show(5)

  }

  case class Flight(
      dest_country_name: String,
      origin_country_name: String,
      count: BigInt
  )

  def readFightDF(
      utils: SparkUtils,
      session: SparkSession
  ): Dataset[Flight] = {
    val option: Map[String, String] =
      Map("inferSchema" -> "true", "header" -> "true", "mode" -> "permissive")
    import session.implicits._
    val path: String =
      raw"src/main/resources/data/flight_data/2015-summary.csv"

    val flightData2015 = session.read
      .format("csv")
      .options(option)
      .load(path)

    flightData2015.as[Flight]

    //    flightData2015.show()
    //    flightData2015.take(10).foreach(row => println(row.length, row(0), row.mkString("-") ) )
    //  flightData2015.sort("count").show(20, true)
  }

  def dataSetsTypeSafe(
      df: Dataset[Flight],
      sparkSession: SparkSession
  ): Unit = {

    df.show(5, false)

    df.filter(f => f.dest_country_name == "United States").show()
  }

}
