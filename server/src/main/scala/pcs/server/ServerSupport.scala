package pcs.server

import akka.actor.{ActorLogging, ActorSystem}
import akka.event.LoggingAdapter
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigRenderOptions
import pcs.server.service.CataloguesService

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

/**
  * Starts and binds server
  */
trait ServerSupport extends CataloguesService {

  implicit val system: ActorSystem = ActorSystem("pcs-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val log: LoggingAdapter = system.log

  def start(): Unit = {
    val config = system.settings.config

    val options = ConfigRenderOptions
      .defaults()
      .setOriginComments(false)
      .setComments(true)
      .setFormatted(true)
      .setJson(false)

    log.debug("Application configuration {}", config.root().render(options))

    val host = config.getString("pcs.server.host")
    val port = config.getInt("pcs.server.port")

    val routes = api // TODO:oshtykhno add another routes

    Http().bindAndHandle(routes, host, port).onComplete {
      case Success(binding) =>
        log.info(s"Server started at ${binding.localAddress} ")
      case Failure(exception) =>
        log.error(exception, "Failed to start server at {}:{}", host, port)
        system.terminate()
    }
  }
}
