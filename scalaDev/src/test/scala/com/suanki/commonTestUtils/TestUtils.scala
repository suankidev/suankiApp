package com.suanki.commonTestUtils

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterAllConfigMap, ConfigMap}

class TestUtils extends AnyFlatSpec with Matchers with BeforeAndAfterAllConfigMap with AbstractSuit {

  var testDate: String = ""

  override def beforeAll(configMap: ConfigMap): Unit = {

    testDate = configMap.get("testData").fold("")(_.toString)
  }

  def testif(query: String, num: Int): String = {

    query match {
      case "count" if num > 5 => "running count test "
      case "count" if num < 5 => "running count test with less number "
    }
  }

  def countTest(tableName: String, query: String): Unit = {

    it should (s"test the ${tableName}") in {
      val finalQuery1 = testif("count", 4) format ("myColumn", "test")

      println(s"final query: $finalQuery1")
      println(s"test date: $testDate")

      val finalQuery: String = buildSelect(finalQuery1)

      finalQuery1 should be(finalQuery)

    }
  }

  def countTestMulti: Unit = {

    it should ("match the records counts for the table")
  }
}
