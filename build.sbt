ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "com.suanki"

lazy val root = (project in file("."))
  .settings(
    name := "root",
	libraryDependencies ++= Seq(
      "org.scala-lang" %% "toolkit" % "0.1.7",
      "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test
    )
	
  )
  .settings(ProjectSettings.root: _*)
  .aggregate(common,scalaDev,sparkWithScala)

lazy val common = (project in file("common"))
  .settings(
  name := "common"
  ).settings(ProjectSettings.common:_*)

 lazy val scalaDev = (project in file("scalaDev"))
  .settings(
  name := "scalaDev"
  )
  .dependsOn(common)

lazy val sparkWithScala = (project in file("sparkWithScala"))
  .settings(
  name := "sparkWithScala"
  ).dependsOn(common)