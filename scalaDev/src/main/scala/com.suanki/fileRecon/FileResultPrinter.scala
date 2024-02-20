package com.suanki.fileRecon

import org.apache.spark.sql.DataFrame

object FileResultPrinter {

  def showResults(
      stgDF: DataFrame,
      finalTableDF: DataFrame,
      sourceFileDF: DataFrame
  ): Unit = {

    println("not implemented!")
  }
}
