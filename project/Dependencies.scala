import sbt._

object Version {
  final val scala = "2.12.5"
  final val akka = "2.5.11"
  final val akkaHttp = "10.1.0"
  final val cats = "1.0.0"
  final val slick = "3.2.3"
}

object Library {
  val akkaActor       = "com.typesafe.akka"  %% "akka-actor"       % Version.akka
  val akkaStreams     = "com.typesafe.akka"  %% "akka-stream"      % Version.akka
  val akkaPersistence = "com.typesafe.akka"  %% "akka-persistence" % Version.akka
  val akkaCluster     = "com.typesafe.akka"  %% "akka-cluster"     % Version.akka
  val akkaRemote      = "com.typesafe.akka"  %% "akka-remote"      % Version.akka
  val akkaHttp        = "com.typesafe.akka"  %% "akka-http"        % Version.akkaHttp
  val cats            = "org.typelevel"      %% "cats-core"        % Version.cats
  val slick           = "com.typesafe.slick" %% "slick"            % Version.slick
}

object Testing {
  val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % Version.akka
}

object Dependencies {

  import Library._

  val common = Seq(
    akkaActor,
    cats
  )

  val persistence = Seq(
    akkaPersistence,
    slick
  )

  val core = Seq(
    akkaCluster
  )

  val server = Seq(
    akkaHttp,
    akkaStreams,
    akkaRemote
  )

}