/*
 * Scala Product Catalogues
 */

//
// Common module
//
lazy val commonSettings = Seq(
  scalaVersion := Version.scala,
  version := "0.1.0-SNAPSHOT",
  libraryDependencies ++= Dependencies.common,
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "utf-8",
    "-explaintypes",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps"
  )
)

lazy val common = project
  .in(file("common"))
  .settings(commonSettings)

//
// Persistence module
//
lazy val persistenceSettings = Seq(
  libraryDependencies ++= Dependencies.persistence
)

lazy val persistence = project
  .in(file("persistence"))
  .settings(commonSettings ++ persistenceSettings)
  .dependsOn(common)

//
// Core module
//
lazy val coreSettings = Seq(
  libraryDependencies ++= Dependencies.core
)

lazy val core = project
  .in(file("core"))
  .settings(commonSettings ++ coreSettings)
  .dependsOn(common, persistence)

//
// Server module
//
lazy val serverSettings = Seq(
  libraryDependencies ++= Dependencies.server
)

lazy val server = project
  .in(file("server"))
  .settings(commonSettings ++ serverSettings)
  .dependsOn(common, core)

//
// Root module
//
lazy val rootSettings = Seq(
  name := "Scala Product Catalogues",
  organization := "shtykhno.net",
  startYear := Some(2017),
  description := "Micro-service with Scala and Akka",
  licenses += "MIT" -> url("https://choosealicense.com/licenses/mit/")
)

lazy val root = project
  .in(file("."))
  .settings(commonSettings ++ rootSettings)
  .dependsOn(server)
  .aggregate(common, core, server, persistence)