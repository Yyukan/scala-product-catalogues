package pcs.server

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import pcs.server.route.{CataloguesRoute, HealthRoute, Routes}
import pcs.server.service._

import scala.util.{Failure, Success}

/**
  * Exposes micro-service REST API
  */
trait ServerSupport extends SystemSupport {

  /* all supported services */
  lazy val api: Seq[Routes] = Seq(
    new CataloguesRoute(new CataloguesService()),
    new HealthRoute()
  )

  /**
    * Starts and binds server
    *
    * Dumps system configuration to the log on DEBUG level
    */
  def start(): Unit = {
    log.debug("Application configuration {}", configAsString)

    val host = config.getString("pcs.server.host")
    val port = config.getInt("pcs.server.port")

    val routes = api
      .map(_.routes)
      .reduce(_ ~ _)

    Http().bindAndHandle(routes, host, port)
      .onComplete {
        case Success(binding) =>
          log.info(s"Server started at ${binding.localAddress}")
        case Failure(exception) =>
          log.error(exception, "Failed to start server at {}:{}", host, port)
          system.terminate()
      }
  }
}
