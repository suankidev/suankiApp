import sbt.Keys.libraryDependencies
import sbt._

object Dependencies {

  lazy val jacocoVersion = "0.8.11"
  lazy val sparkCore = "org.apache.spark" %% "spark-core" % "3.3.2"

  lazy val sparksql="org.apache.spark"           %% "spark-sql"         % "3.3.2"
  lazy val jdbc8 ="com.oracle.database.jdbc"    % "ojdbc8"            % "23.3.0.23.09"
  lazy val jacoco = "org.jacoco"                  % "org.jacoco.core"   % jacocoVersion
  lazy val  jacocoRep = "org.jacoco"                  % "org.jacoco.report" % jacocoVersion
  lazy val scalaTest = "org.scalatest"              %% "scalatest"         % "3.2.17" % Test
  lazy val mockito = "org.mockito"                 % "mockito-core"      % "5.2.0"  % Test
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"     % "3.9.5"
  lazy val slf4j = "org.slf4j"                   % "slf4j-api"         % "2.0.5"
  lazy val log4j = "org.slf4j" % "slf4j-log4j12" % "2.0.5" exclude ("log4j", "log4j")
  lazy val logj = "log4j"     % "log4j"         % "1.2.17"
  lazy val jdbc6 = "com.oracle.database.jdbc" % "ojdbc6" % "11.2.0.4"

  private def depends(modules: ModuleID*): Seq[Def.Setting[Seq[ModuleID]]] = Seq(libraryDependencies ++= modules)

  lazy val common = depends(sparksql,sparkCore,jdbc8)


  object Spark {
    lazy val core = "org.apache.spark" %% "spark-core" % Versions.spark % Provided
    lazy val sql = "org.apache.spark" %% "spark-sql" % Versions.spark % Provided
    lazy val streaming = "org.apache.spark" %% "spark-streaming" % Versions.spark % Provided
    lazy val mlLib = "org.apache.spark" %% "spark-mllib" % Versions.spark % Provided
  }
}
