package com.suanki.commonTestUtils

trait AbstractSuit {

  var finalQuery: String = ""

  def buildSelect(query: String): String =
    query + "test"

}
