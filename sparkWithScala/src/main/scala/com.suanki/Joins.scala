package com.suanki

import org.apache.spark.sql.{DataFrame, SparkSession}
class Joins(spark: SparkSession) {

  def innerJoin(): Unit = {

    val deptDf = getJoinDataSets._1
    val empDf  = getJoinDataSets._2

    deptDf.show(truncate = false)
    empDf.show(truncate = false)

  }

  def getJoinDataSets: (DataFrame, DataFrame) = {
    import spark.implicits._
    val emp = Seq(
      (1, "Smith", -1, "2018", "10", "M", 3000),
      (2, "Rose", 1, "2010", "20", "M", 4000),
      (3, "Williams", 1, "2010", "10", "M", 1000),
      (4, "Jones", 2, "2005", "10", "F", 2000),
      (5, "Brown", 2, "2010", "40", "", -1),
      (6, "Brown", 2, "2010", "50", "", -1)
    )
    val empColumns = Seq("emp_id", "name", "superior_emp_id", "year_joined", "emp_dept_id", "gender", "salary")

    val empDF = emp.toDF(empColumns: _*)

    val dept = Seq(("Finance", 10), ("Marketing", 20), ("Sales", 30), ("IT", 40))

    val deptColumns = Seq("dept_name", "dept_id")
    val deptDF      = dept.toDF(deptColumns: _*)

    (empDF, deptDF)

  }

}
