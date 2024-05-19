package com.suanki.sourcing.oracleDomain

import com.suanki.sparkUtils.Spark
import org.apache.spark.sql.DataFrame

object Main extends App with Spark {

  def fetchTable(tableName: String): DataFrame = {
    val driver = raw"oracle.jdbc.driver.OracleDriver"

    spark.read
      .format("jdbc")
      .option("url", "jdbc:oracle:thin:suanki/yourpassword@//localhost:1521/PDBORCL")
      .option("user", "suanki")
      .option("password", "yourpassword")
      .option("driver", driver)
      .option("dbtable", tableName)
      .load()

  }

  val regionDf           = fetchTable("regions")
  val countriesDf        = fetchTable("countries")
  val locations          = fetchTable("locations")
  val warehouses         = fetchTable("warehouses")
  val employees          = fetchTable("employees")
  val product_categories = fetchTable("product_categories")
  val products           = fetchTable("products")
  val customers          = fetchTable("customers")
  val contacts           = fetchTable("contacts")
  val orders             = fetchTable("orders")
  val order_items        = fetchTable("order_items")
  val inventories        = fetchTable("inventories")

  regionDf.show(5, false)
  regionDf.write
    .format(("parquet"))
    .mode("overwrite")
    .save("C:\\Users\\sujee\\Desktop\\spark_input\\oracleData\\regions")

  val listofDf = {
    List(regionDf,
         countriesDf,
         locations,
         warehouses,
         employees,
         product_categories,
         products,
         customers,
         contacts,
         orders,
         order_items,
         inventories
    )
  }
  val tableList = {
    List(
      "regions",
      "countries",
      "locations",
      "warehouses",
      "employees",
      "product_categories",
      "products",
      "customers",
      "contacts",
      "orders",
      "order_items",
      "inventories"
    )
  }
  for (idx <- 0 until listofDf.length) {

    listofDf(idx).write
      .format("parquet")
      .mode("overwrite")
      .save(s"C:\\Users\\sujee\\Desktop\\spark_input\\oracleData\\${tableList(idx)}")
  }

}
