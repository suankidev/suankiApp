package com.suanki.dev.paramParsing

import com.typesafe.config.{Config, ConfigFactory}

object AppConfig {

  val configFile: Config = ConfigFactory.load("application.conf")
}
