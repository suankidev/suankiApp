package com.suanki

import com.suanki.sparkUtils.{AppLogger, CommonUtils, SparkUtils}
import com.suanki.stgTransformer.{ExerciseOnDF, SparkOptimization}

object SuankiTransformer {

  def main(args: Array[String]): Unit = {
    val appLogger = AppLogger.getAppLogger(this)
    AppLogger.getInfoMsg("Starting Transformer ", appLogger)

    val sparkUtil  = new SparkUtils()
    val commonUtil = new CommonUtils(sparkUtil.getSparkSession())

    val exerciseOnDF =
      new ExerciseOnDF(sparkUtil.getSparkSession(), commonUtil)

    val sparkOptimization =
      new SparkOptimization(sparkUtil.getSparkSession(), commonUtil)
    sparkOptimization.narrowTransformation()

    // Thread.sleep(90000)
    AppLogger.getInfoMsg("End of the Program !", appLogger)

  }
}
