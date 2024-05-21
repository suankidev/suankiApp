package com.suanki.sourcing

import org.apache.spark.sql.SparkSession

class DemoSparkSql(spark: SparkSession) {

  def execute: Unit = {
    println("inside demo spark")

    spark.sql("create database if not exists demo")
    spark.sql("""
                |create  table demo.demo_stg(
                |  id String,
                |  name String,
                |  age  String,
                |  salary String
                |);
                |
                |
                |""".stripMargin)

    spark.sql("""
                |create  table demo.demo_final(
                |  id int,
                |  name String,
                |  age  int,
                |  salary decimal(9,3)
                |);
                |
                |""".stripMargin)

    spark.sql("""
                |insert into demo.demo_stg
                |values
                |('1','ajay','898.343'),
                |('1','kamara','7347.33');
                |""".stripMargin)

    spark.sql("""
                |insert overwrite into  demo.demo_final
                |select id, name,age, salary from demo.demo_stg;
                |""".stripMargin)

    spark.table("demo.demo_stg").show(truncate = false)
    spark.table("demo.demo_final").show(truncate = false)
  }

}
