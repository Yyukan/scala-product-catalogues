import sbt._

object Version {
  val scala = "2.12.5"
  val akka = "2.5.11"
  val akkaHttp = "10.1.0"
  val akkaSwagger = "0.14.0"
  val akkaSwaggerUI = "1.1.0"
  val cats = "1.0.0"
  val slick = "3.2.3"
  val scalaTest = "3.0.5"
  val mockito = "2.18.0"
}

object Library {
  val akka            = "com.typesafe.akka"            %% "akka-actor"           % Version.akka
  val akkaStreams     = "com.typesafe.akka"            %% "akka-stream"          % Version.akka
  val akkaPersistence = "com.typesafe.akka"            %% "akka-persistence"     % Version.akka
  val akkaCluster     = "com.typesafe.akka"            %% "akka-cluster"         % Version.akka
  val akkaRemote      = "com.typesafe.akka"            %% "akka-remote"          % Version.akka
  val akkaHttp        = "com.typesafe.akka"            %% "akka-http"            % Version.akkaHttp
  val akkaJson        = "com.typesafe.akka"            %% "akka-http-spray-json" % Version.akkaHttp
  val akkaSwagger     = "com.github.swagger-akka-http" %% "swagger-akka-http"    % Version.akkaSwagger
  val akkaSwaggerUI   = "co.pragmati"                  %% "swagger-ui-akka-http" % Version.akkaSwaggerUI
  val cats            = "org.typelevel"                %% "cats-core"            % Version.cats
  val slick           = "com.typesafe.slick"           %% "slick"                % Version.slick
}

object Testing {
  val akkaTestKit     = "com.typesafe.akka"            %% "akka-testkit"         % Version.akka % Test
  val akkaHttpTestKit = "com.typesafe.akka"            %% "akka-http-testkit"    % Version.akkaHttp % Test
  val scalactic       = "org.scalactic"                %% "scalactic"            % Version.scalaTest % Test
  val scalatest       = "org.scalatest"                %% "scalatest"            % Version.scalaTest % Test
  val mockito         = "org.mockito"                  %  "mockito-core"         % Version.mockito % Test
}

object Dependencies {

  import Library._
  import Testing._

  val common = Seq(
    akka,
    cats,
    scalactic,
    scalatest,
    mockito
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
    akkaHttpTestKit,
    akkaJson,
    akkaSwagger,
    akkaSwaggerUI,
    akkaStreams,
    akkaRemote
  )

}