import sbt.Keys.libraryDependencies
import sbt._

object Dependencies {

  lazy val classDependencyCompile     = "compile->compile"
  lazy val classDependencyTest        = "test->test"
  lazy val classDependencyCompileTest = "test->test;compile->compile"

  lazy val jacocoVersion = "0.8.11"
  lazy val jdbc8         = "com.oracle.database.jdbc"   % "ojdbc8"        % "23.3.0.23.09"
  lazy val jdbc6         = "com.oracle.database.jdbc"   % "ojdbc6"        % "11.2.0.4"
  lazy val scalaLogging  = "com.typesafe.scala-logging" % "scala-logging" % "3.9.5"
  lazy val gitScopt      = "com.github.scopt"          %% "scopt"         % "4.0.1"
  lazy val configFactory = "com.typesafe"               % "config"        % "1.4.2"

  lazy val common =
    depends(
      jdbc8,
      Spark.core,
      Spark.sql,
      Spark.streaming,
      Testing.scalaTest,
      Testing.scalaTestFlatspec
    )

  lazy val scalaDev =
    depends(Spark.core,
            Spark.sql,
            Spark.streaming,
            Testing.scalaTest,
            Testing.scalaTestFlatspec,
            configFactory,
            gitScopt
    )

  lazy val sparkWithScala =
    depends(
      Spark.core,
      Spark.sql,
      Spark.streaming,
      Testing.scalaTest,
      Testing.scalaTestFlatspec,
      configFactory,
      gitScopt
    )

  private def depends(modules: ModuleID*): Seq[Def.Setting[Seq[ModuleID]]] = Seq(libraryDependencies ++= modules)

  object Spark {
    lazy val core      = "org.apache.spark" %% "spark-core"      % Versions.spark // % Provided
    lazy val sql       = "org.apache.spark" %% "spark-sql"       % Versions.spark // % Provided
    lazy val streaming = "org.apache.spark" %% "spark-streaming" % Versions.spark // % Provided
    lazy val mlLib     = "org.apache.spark" %% "spark-mllib"     % Versions.spark // % Provided
  }

  object Testing {
    lazy val scalaTest             = "org.scalatest"     %% "scalatest"          % Versions.scalaTest % Test
    lazy val scalaTestFlatspec     = "org.scalatest"     %% "scalatest-flatspec" % Versions.scalaTest % Test
    lazy val scalaTestMockitoSugar = "org.scalatestplus" %% "mockito-3-4"        % "3.2.10.0"         % Test
    lazy val mockito               = "org.mockito"        % "mockito-core"       % Versions.mockito   % Test
  }

}
