package com.suanki.dev.oops

import java.util.{Date => javaUtilDate}

class PackageAndImport {

  def testIfPackageObjectAccessible = {
    println(SPEED_OF_LIGHT)
  }

  def workWithDiffrentDates: Unit = {
    val javaDate = new javaUtilDate(System.currentTimeMillis)
//    val sqlData = new javaSqlDate(2018,12,30)
//    println(javaDate)
//    println(sqlData)
  }

  // default import
  // java.lang
  // scala, type
}
