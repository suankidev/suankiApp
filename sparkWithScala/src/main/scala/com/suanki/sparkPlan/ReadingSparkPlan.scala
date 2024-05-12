package com.suanki.sparkPlan

import com.suanki.sparkUtils.Spark
import org.apache.spark.sql.functions._

class ReadingSparkPlan extends Spark {

  def narrow_plan: Unit = {

    val path =
      "C:\\Users\\sujee\\Desktop\\spark_input\\spark-experiments-main\\data\\data_skew\\customers.parquet"
    val custDF = spark.read.parquet(path)

    val finalDf =
      custDF
        .filter(col("city") === "boston")
        .withColumn("first_name", split(col("name"), " ")(0))
        .withColumn("last_name", split(col("name"), " ")(1))
        .withColumn("age", col("age") + lit(5))
        .selectExpr(
          "cust_id",
          "first_name",
          "last_name",
          "age",
          "gender",
          "birthday"
        )

    finalDf.write
      .format("noop")
      .mode("overwrite")
      .save("C:\\Users\\sujee\\Desktop\\spark_output\\customer")

  }

}

object ReadingSparkPlan extends App {

  val sparkPlan = new ReadingSparkPlan
  sparkPlan.narrow_plan

  Thread.sleep(9000)
}
