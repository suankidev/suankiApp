package com.suanki.paramParsing

case class StagingApi(ctx: ParamParser with Spark) extends Api {

  val apiObject           = AppConfig.configFile.getConfig("some.api").getConfig(ctx.param.sourceName)
  var callApiDF: String   = "actual result will be df"
  val api: F1Api          = getApi(ctx)
  val url: String         = apiObject.getString("url")
  val targetTable: String = apiObject.getString("targetTable")
  val partition: String   = apiObject.getString("partition")

//  callApiDF = F1Api(apiObject.getString(ctx.sourceName), "").fetchApi

  def run: Unit = {

    println(apiObject)
    println(url)
    println(targetTable)
    println(s"partition: $partition")

    val splitted                          = partition.split(",")
    var partitionMap: Map[String, String] = Map()
    splitted foreach (x => {
      x match {
        case "date" => partitionMap += ("date" -> ctx.param.date)
        case "id"   => partitionMap += ("id"   -> ctx.param.id.toString)
      }
    })

    partitionMap.values foreach println

  }

}
