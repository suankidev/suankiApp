package com.suanki

import commonTestUtils.TestUtils

class FavouriteTest extends TestUtils {

  val tableName = "test_table"
  val query     = "select distinct(count(%s)) from %s"

  countTest(tableName, query)

}
