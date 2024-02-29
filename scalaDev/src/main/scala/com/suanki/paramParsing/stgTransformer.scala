package com.suanki.paramParsing

object stgTransformer extends ParamParser with Spark {

  val sourceName: Boolean = param.sourceName.toLowerCase.endsWith("api")

  sourceName match {
    case name @ true  => StagingApi(this).run
    case name @ false => StagingTable(this).run
  }

  println("==" * 30)
}
