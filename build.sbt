//ThisBuild / scalaVersion := "2.13.12"
//ThisBuild / organization := "com.suanki"

lazy val root = (project in file("."))
  .settings(
    name := "suankiApp"
  )
  .settings(ProjectSettings.root: _*)
  .aggregate(common, scalaDev, sparkWithScala)

lazy val common = (project in file("common"))
  .settings(
    name := "common"
  )
  .withId("common")
  .settings(ProjectSettings.common: _*)

lazy val scalaDev = (project in file("scalaDev"))
  .settings(
    name := "scalaDev"
  )
  .settings(ProjectSettings.scalaDev: _*)
  .withId("scalaDev")
  .dependsOn(common % Dependencies.classDependencyCompileTest)

lazy val sparkWithScala = (project in file("sparkWithScala"))
  .settings(
    name := "sparkWithScala"
  )
  .withId("sparkWithScala")
  .settings(ProjectSettings.sparkWithScala: _*)
  .dependsOn(common % Dependencies.classDependencyCompileTest)
