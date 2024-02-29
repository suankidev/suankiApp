package com.suanki.dev.jdbOperation

import oracle.jdbc.driver.OracleDriver

import java.sql.{Connection, DriverManager, ResultSet, Statement}
import scala.collection.mutable.ListBuffer

class JdbcFetch {

  /** val productDF = spark.read.format("jdbc") .option("url",
    * "jdbc:oracle:thin:suanki/testpass@//localhost:1521/PDBORCL") .option("driver", "oracle.jdbc.driver.OracleDriver")
    * .option("user", "suanki") .option("password", "TestPass#123") .option("dbtable", "product").load()
    * ----------------------------------------- -------- ---------------------------- PRODUCT_ID NOT NULL NUMBER(12)
    * WAREHOUSE_ID NOT NULL NUMBER(12) QUANTITY NOT NULL NUMBER(8)
    * @return
    */

  def executeJdbcQuery(): Unit = {

//    val DB_URL = "jdbc:oracle:thin:suanki/TestPass#123@//localhost:1521/PDBORCL"
//    val DB_URL = "jdbc:oracle:thin:@localhost:1521/PDBORCL"
    // val d=DriverManager.getConnection("jdbc:oracle:thin:suanki/lemktpr@//localhost:1521/PDBORCL")
//          println(d.isClosed)
//           d.close()
    val DB_URL = "jdbc:oracle:thin:@//localhost:1521/PDBORCL"
    val USER   = "suanki"
    val PASS   = "TestPass#123"
    val QUERY =
      "select PRODUCT_ID, WAREHOUSE_ID, QUANTITY from INVENTORIES"
    val count_columns =
      """select count(PRODUCT_ID) as product_id, count(WAREHOUSE_ID) as WAREHOUSE_ID,
                            count(QUANTITY) as QUANTITY from INVENTORIES"""

    val columns = "product_id, warehouse_id, quantity".split(",")

    val QUERY_COUNT = "select count(*) as count from INVENTORIES"

    val driver = new oracle.jdbc.driver.OracleDriver()
    DriverManager.registerDriver(driver)

    val conn: Connection = DriverManager.getConnection(
      "jdbc:oracle:thin:suanki/TestPass#123@localhost:1521/PDBORCL"
    )
    val stmt: Statement = conn.createStatement()
    val rslt: ResultSet = stmt.executeQuery(count_columns)

    val finalResult: ListBuffer[String] = ListBuffer.empty

    while (rslt.next()) {
      for (i <- 0 until (columns.length)) {

        finalResult += rslt.getString(columns(i).trim)

      }
    }

    finalResult foreach println

    conn.close()

  }

}

object JdbcFetch {

  def main(args: Array[String]): Unit = {

    val jdbcFetch = new JdbcFetch()
    jdbcFetch.executeJdbcQuery()

  }

  def testFile(
      firstparameter: String,
      firstparameter1: String,
      firstparameter2: String,
      firstparameter3: String,
      thirdParameter: String
  ): Unit =
    println("thisis test file")

}
