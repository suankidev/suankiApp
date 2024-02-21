package com.suanki.stgTransformer

import com.suanki.sparkUtils.CommonUtils
import org.apache.spark.sql.functions.{approx_count_distinct, col, count, countDistinct, expr}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{SaveMode, SparkSession}

class BasicOfSpark(spark: SparkSession, util: CommonUtils) {

  def dataFrames(): Unit = {

    println("spark dataframe")

    val firstDf = spark.read
      .format("json")
      .option("inferSchema", "false")
      .load("src/main/resources/data/cars.json")

    firstDf.show(truncate = false)
    firstDf.printSchema()

    /** root \|-- Acceleration: double (nullable = true) \|-- Cylinders: long (nullable = true) \|-- Displacement:
      * double (nullable = true) \|-- Horsepower: long (nullable \= true) \|-- Miles_per_Gallon: double (nullable =
      * true) \|-- Name: string (nullable = true) \|-- Origin: string (nullable = true) \|-- Weight_in_lbs: long
      * (nullable \= true) \|-- Year: string (nullable = true)
      */

    // get rows
    val first10Row = firstDf.take(10)

    first10Row.foreach(i => println(i))

    // spark types
    val longType = LongType

    // schema
    // schema
    val carsSchema = StructType(
      Array(
        StructField("Name", StringType),
        StructField("Miles_per_Gallon", DoubleType),
        StructField("Cylinders", LongType),
        StructField("Displacement", DoubleType),
        StructField("Horsepower", LongType),
        StructField("Weight_in_lbs", LongType),
        StructField("Acceleration", DoubleType),
        StructField("Year", StringType),
        StructField("Origin", StringType)
      )
    )

    val carsDFWithSchema = spark.read
      .format("json")
      .schema(carsSchema)
      .load("src/main/resources/data/cars.json")

    /*create manual smarphone schema

     */

    val smartphone = Seq(
      ("Nokia", "3300"),
      ("Samsung", "2200"),
      ("Test", "3300")
    )

    val smartphoneDF = spark.createDataFrame(smartphone)

  }

  def createDFByHand() = {

    // create rows by hand
    import org.apache.spark.sql.Row
    val myRow = Row("Sujeet", "23", "B.Tech")

    val emp = Seq(
      (1, "Smith", -1, "2018", "10", "M", 3000),
      (2, "Rose", 1, "2010", "20", "M", 4000),
      (3, "Williams", 1, "2010", "10", "M", 1000),
      (4, "Jones", 2, "2005", "10", "F", 2000),
      (5, "Brown", 2, "2010", "40", "", -1),
      (6, "Brown", 2, "2010", "50", "", -1)
    )

    val empColumns = Seq(
      "emp_id",
      "name",
      "superior_emp_id",
      "year_joined",
      "emp_dept_id",
      "gender",
      "salary"
    )

    // schema auto inffered
    val empDF = spark.createDataFrame(emp)

    empDF.show()

    // create DFS with implicits
    import spark.implicits._
    val empToDF = emp.toDF(
      "emp_id",
      "name",
      "superior_emp_id",
      "year_joined",
      "emp_dept_id",
      "gender",
      "salary"
    )

    empToDF.show(truncate = false)

    val dept =
      Seq(("Finance", 10), ("Marketing", 20), ("Sales", 30), ("IT", 40))

    val deptColumns = Seq("dept_name", "dept_id")
    val deptDF      = dept.toDF("dept_name", "dept_id")

  }

  def howManyJobsAndStages() = {

    val path = "C:\\Users\\sujee\\Desktop\\spark_input\\fligt_data\\"
    val surveydf =
      "C:\\Users\\sujee\\Desktop\\spark_input\\stack-overflow-developer-survey-2019\\survey_results_public.csv"
    val flightDf2010 =
      spark.read
        .format("csv")
        .option("header", true)
        .load(path + "2010*.csv")
    val flightDf2011 =
      spark.read
        .format("csv")
        .option("header", true)
        .load(path + "2011*.csv")
    val flightDf2015 =
      spark.read
        .format("csv")
        .option("header", true)
        .load(path + "2015*.csv")
    val stockSurvey2019 =
      spark.read.format("csv").option("header", true).load(surveydf)
    val allretailer = spark.read
      .format("csv")
      .option("sep", "|")
      .option("header", true)
      .load(
        "C:\\Users\\sujee\\Desktop\\spark_input\\retailer\\data\\inventory.dat"
      )

    println(flightDf2010.rdd.getNumPartitions)

    println(s"flight df count ${flightDf2010.count()}")

    spark.conf.set("spark.sql.shuffle.partitions", 5)
//   println(allretailer.rdd.getNumPartitions) //2
//   println(flightDf2010.rdd.getNumPartitions) //1
    // no of jobs till here is 5

//    allretailer.where(col("inv_quantity_on_hand") > 500 ).count()

    val df = flightDf2010
      .groupBy(col("DEST_COUNTRY_NAME"))
      .agg(count(col("count")).alias("counts"))
      .select("DEST_COUNTRY_NAME", "counts")

    // df.show()
    println(df.rdd.getNumPartitions)

    df.write
      .format("csv")
      .mode(SaveMode.Overwrite)
      .option("header", true)
      .csv(
        "C:\\Users\\sujee\\Desktop\\spark_output\\warehouse_dir\\retail_data"
      )

  }

  def dfReadMode(): Unit = {

    // https://medium.com/@sasidharan-r/what-are-the-lists-of-available-read-modes-in-spark-with-examples-e17455575c9b
    /** * ->Drop the corrupted records ->Handle the corrupted records and store them a separate location ->Fail the job
      * if we get corrupted record
      */

    /*
    failFast
    dropMalformed
    permissive (default)
     */

    val path = raw"src/main/resources/data/bands_readmode_tester.json"

    /** {"id":1,"name":"AC/DC","hometown":"Sydney","year":1973} {"id":0,"name":"Led
      * Zeppelin","hometown":"London","year":1968} {"id":3,"name":"Metallica","hometown":"Los Angeles","year":1981}
      * {"id":4,"name":"The Beatles","hometown":10,20,"test","year":1960}
      */

    // failFast
    // hometown : last record is integer will be ignored
    val dfSchema = StructType(
      Array(
        StructField("id", IntegerType),
        StructField("name", StringType),
        StructField("hometown", StringType),
        StructField("year", StringType),
        StructField("_corrupt_record", StringType)
      )
    )

    val failFastDf = spark.read
      .format("json")
      .schema(dfSchema)
      .option("columnNameofCorruptRecord", "_corrupt_record")
      .option("mode", "permissive")
      .load(path)

    failFastDf.show(truncate = false)

    // Alternative reading with option map

    val carsDF = spark.read
      .format("json")
      .options(
        Map(
          "inferSchema" -> "true",
          "header"      -> "true",
          "path"        -> raw"src/main/resources/data/cars.json"
        )
      )
      .load()

    // writing a df
    // SaveMode
    carsDF.write
      .format("csv")
      .mode(SaveMode.Overwrite)
      .save(raw"src/main/resources/out/cars")

    /** opions: json flag
      *
      * allowSinglQuotes -> true/false compression -> uncomressed //bazip2, gzip, snappy
      *
      * csv: options
      *
      * header dateFormate nullvalue
      */

    val productDF = spark.read
      .format("jdbc")
      .option("url", "jdbc:oracle:thin:suanki/testpass@//localhost:1521/PDBORCL")
      .option("driver", "oracle.jdbc.driver.OracleDriver")
      .option("user", "suanki")
      .option("password", "testpass")
      .option("dbtable", "product")
      .load()

    productDF.show()

  }

  def ColumnAndExpression(): Unit = {

    val carsDf = util.readDF(
      path = raw"src/main/resources/data/cars.json",
      filetype = "json",
      opt = Map("inferSchema" -> "true", "header" -> "true"),
      isSchema = false,
      dfSchema = null
    )

    carsDf.show()

    // selecting a column

    val Origin  = col("Origin")
    val iOrigin = carsDf.col("Origin")
    carsDf.select(
      Origin,
      iOrigin,
      expr("origin")
    )

    carsDf.select("origin", "horsepower")

    // Expression

    val simplestExpression  = carsDf.col("Weight_in_lbs")
    val weightInKgExprssion = carsDf.col("Weight_in_lbs") / 2
    val usingexpre          = expr("Weight_in_lbs / 2").alias("weighInKg")

    val powerfullCarsDF =
      carsDf.filter((col("origin") === "USA").and(col("horspower") >= 150))

  }

  def AggregationOnColumn = {

    val moviesDF = util.readDF(
      path = raw"C:\Users\sujee\Desktop\spark-sbt-dev\src\main\resources\data\movies.json",
      filetype = "json",
      Map("inferSchema" -> "true", "header" -> "true"),
      isSchema = false
    )

    moviesDF.select(count("title")) // will exclude null
    moviesDF.select(count("*"))     // will include null
    moviesDF.select(countDistinct(col("Major_Genre")))
    moviesDF.select(approx_count_distinct("Major_Genre"))

  }

}
