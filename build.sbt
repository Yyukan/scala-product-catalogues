/*
  Scala Product Catalogues
*/
lazy val commonSettings = Seq(
  scalaVersion := Version.scala
)

lazy val main =
  project
    .in(file("."))
    .settings(commonSettings)
