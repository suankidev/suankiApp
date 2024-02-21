package com.suanki.stgTransformer

import com.suanki.sparkUtils.CommonUtils
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Encoders, SparkSession}
class ExerciseOnDF(spark: SparkSession, util: CommonUtils) {

  def ColumnAndExpressionEx = {

    // read the movie df and select 2 column
    // column having total profit of the movies = US_Gross + Worldwide_Gross
    // select movie with imdb rating above 6

    val moviesDF = util.readDF(
      path = raw"C:\Users\sujee\Desktop\spark-sbt-dev\src\main\resources\data\movies.json",
      filetype = "json",
      Map("inferSchema" -> "true", "header" -> "true"),
      isSchema = false
    )

    val totalProfitDf =
      moviesDF.withColumn("total_profit", col("US_Gross") + col("Worldwide_Gross"))

    totalProfitDf
      .where(
        col("Major_Genre").isNotNull && lower(
          col("Major_Genre")
        ) === "comedy"
      )
      .select("Title", "total_profit", "IMDB_Rating")

    // Q. Sum up all the profits of all the movies in df

    val profitSum =
      (col("US_Gross") + col("Worldwide_Gross")).alias("total_profit")

    // Q. count how many distinct dir

    val distinctdir = countDistinct(col("director"))
    // or
    moviesDF.select("director").distinct().count()

    // Q comput the avg imdb rating and avg us fross revenue per dir

    moviesDF
      .groupBy(col("director"))
      .agg(avg(col("imdb_rating")), sum(col("us_gross")))

  }

  def sparkTypeAndDatasets = {
    val moviesDF = util.readDF(
      path = raw"C:\Users\sujee\Desktop\spark-sbt-dev\src\main\resources\data\movies.json",
      filetype = "json",
      Map("inferSchema" -> "true", "header" -> "true"),
      isSchema = false
    )

    // adding a plane vlaue to a df

    moviesDF.select(col("title"), lit("47"))

    // boolean
    val dramaFilter = col("major_genre").equalTo("Drama")
    moviesDF.filter(dramaFilter).select("title")

    val numberDF = spark.read
      .format("csv")
      .option("inferSchema", true)
      .option("header", true)
      .load(raw"src/main/resources/data/numbers.csv")

//    numberDF.show()
//    numberDF.printSchema()

    implicit val intEncoder = Encoders.scalaInt

    val numberDS = numberDF.as[Int]
    numberDS.filter(_ < 100)

    // for multiple columns
    import spark.implicits._

    // implicit val carEncoder = Encoders.product[Car]

    val carsDF = util.readDFWithFileName("cars")
    val carDS  = carsDF.as[Car]

    carsDF.show()
    // map, flatMap, fold, reduce, for comprension
    val carNameDS = carDS.map(car => car.Name.toUpperCase())

  }

}
