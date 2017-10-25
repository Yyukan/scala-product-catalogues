/*
 * Scala Product Catalogues
 */

lazy val commonSettings = Seq(
  scalaVersion := Version.scala,
  version := "0.1.0-SNAPSHOT"
)

lazy val common = project
  .in(file("common"))
  .settings(commonSettings)

lazy val persistence = project
  .in(file("persistence"))
  .settings(commonSettings)
  .dependsOn(common)

lazy val core = project
  .in(file("core"))
  .settings(commonSettings)
  .dependsOn(common, persistence)

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
lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .dependsOn(server)
  .aggregate(common, core, server, persistence)

