package com.suanki.paramParsing

trait Api extends Spark {

  def getApi(ctx: ParamParser with Spark) = F1Api(ctx)

}

trait ApiEndPoint {
  def fetchApi: String
}

class F1Api(param: ParamParser with Spark) extends ApiEndPoint {

  def fetchApi: String = {
// calliapi
    "will return dataframe in actual call"
  }
}

object F1Api {

  def apply(ctx: ParamParser with Spark): F1Api = new F1Api(ctx: ParamParser with Spark)

}
