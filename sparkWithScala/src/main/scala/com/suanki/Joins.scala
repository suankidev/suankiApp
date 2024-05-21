package com.suanki

import org.apache.spark.sql.catalyst.plans.Inner
import org.apache.spark.sql.{DataFrame, SparkSession}

class Joins(spark: SparkSession) {

  def innerJoin(): Unit = {

    val empDF  = getJoinDataSets._1
    val deptDF = getJoinDataSets._2

    empDF.createOrReplaceTempView("emp")
    deptDF.createOrReplaceTempView("dept")
    deptDF.show(truncate = false)
    empDF.show(truncate = false)
    val joinExprs = empDF.col("emp_dept_id") === deptDF.col("dept_id")
    val joinType  = Inner

    val innerJoinDF = empDF.join(deptDF, joinExprs = joinExprs, joinType = "inner")

    val leftJoin = empDF.join(deptDF, joinExprs = joinExprs, joinType = "left")
    val right    = empDF.join(deptDF, joinExprs = joinExprs, joinType = "right")
    val leftSemi = empDF.join(deptDF, joinExprs = joinExprs, joinType = "leftsemi")
    val leftAnti = empDF.join(deptDF, joinExprs = joinExprs, joinType = "leftanti")

//    innerJoinDF.show(truncate = false)
//    leftJoin.show(false)
//    right.show(truncate = false)
//    leftSemi.show(false)
//    leftAnti.show(truncate = false)

//    spark.sql("select * from emp e inner join dept d on e.emp_dept_id = d.dept_id").show()
//    spark.sql("select * from emp e inner join dept d on e.emp_cid = d.dept_id").show()
    spark
      .sql(
        s"""
           |with temp as(
           |select * from emp e inner join dept d on e.emp_dept_id = d.dept_acct
           |),
           |in_claus as(
           |select * from emp e inner join dept d on e.emp_cid = d.dept_id
           |),
           |final as (
           |select * from temp
           |union
           |select * from in_claus
           |)select 
           |   emp_id,name ,superior_emp_id,
           |   year_joined,emp_dept_id,
           |   gender,company ,salary,emp_cid,
           |   dept_name,dept_id,
           |   case 
           |    when cast(dept_acct as int)=118 then 9999
           |    when cast(dept_acct as int)=114 then    1000
           |    else ' '
           |    end as dept_acct
           | from final 
           |
           |""".stripMargin
      )
      .show(truncate = false)

  }

  def getJoinDataSets: (DataFrame, DataFrame) = {
    import spark.implicits._
    val emp = Seq(
      (1, "Smith", -1, "2018", "10", "M", "ABC LTD", 3000, 10),
      (2, "Rose", 1, "2010", "20", "M", "PQR co ltd", 4000, 10),
      (3, "Williams", 1, "2010", "10", "M", "XYZ corp", 1000, 10),
      (4, "Jones", 2, "2005", "10", "F", "XYZ corp", 2000, 40),
      (5, "Brown", 2, "2010", "40", "", "ABC LTD", -1, 40),
      (6, "Brown", 2, "2010", "50", "", "PQR co ltd", -1, 50),
      (6, "Brown", 2, "2010", "50", "", "ABC LTD", -1, 20),
      (6, "Brown", 2, "2010", "50", "", "ABC LTD", -1, 20)
    )
    val empColumns =
      Seq("emp_id", "name", "superior_emp_id", "year_joined", "emp_dept_id", "gender", "company", "salary", "emp_cid")

    val empDF = emp.toDF(empColumns: _*)

    val dept = Seq(
      ("Finance", 10, 113),
      ("Marketing", 20, 114),
      ("Sales", 30, 114),
      ("IT", 40, 118)
    )

    val deptColumns = Seq("dept_name", "dept_id", "dept_acct")
    val deptDF      = dept.toDF(deptColumns: _*)

    (empDF, deptDF)

  }

}
