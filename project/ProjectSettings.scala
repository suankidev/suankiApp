import sbt.Keys._
import sbt._

object ProjectSettings {

  lazy val root           = commonSettings
  lazy val scalaDev       = commonSettings ++ Dependencies.scalaDev
  lazy val sparkWithScala = commonSettings ++ Dependencies.sparkWithScala
  lazy val common         = commonSettings ++ Dependencies.common

  private lazy val general = Seq(
    version          := version.value,
    scalaVersion     := Versions.scala,
    organization     := "com.suanki",
    organizationName := "suankiDev",
    developers       := List(Developer("Sujeet", "sujeet Singh", "test@mail.com", new URL("http://dev.com"))),
    // scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfuture","-Xlint"),
    javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled"),
    cancelable in Global := true // allow to use Ctrl + C in sbt prompt
  )
//  private lazy val commonSettings = general ++ Testing.settings ++ Publish.settings ++ Keys.settings ++ Assembly.settings
  private lazy val commonSettings = general
}
