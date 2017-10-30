package pcs.server

import akka.actor.Props
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import pcs.core.sample.cluster.simple.SimpleClusterListener
import pcs.server.service.{CataloguesService, CategoryService, Service}

import scala.util.{Failure, Success}

/**
  * Exposes micro-service REST API
  */
trait ServerSupport extends SystemSupport {

  /* all supported services */
  lazy val services: Seq[Service] = Seq(
    CategoryService(system),
    CataloguesService(system)
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

    val routes = services
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

    val ref = system.actorOf(Props[SimpleClusterListener], name = "clusterListener")

    println(s"Ref $ref")

  }
}
