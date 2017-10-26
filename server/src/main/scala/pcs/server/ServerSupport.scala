package pcs.server

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigRenderOptions
import pcs.server.service.{CataloguesService, CategoryService, Service}

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

/**
  * Exposes micro-service REST API
  */
trait ServerSupport {

  implicit val system: ActorSystem = ActorSystem("server-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val log: LoggingAdapter = system.log

  /* all supported services */
  val services: Seq[Service] = Seq(
    CategoryService(),
    CataloguesService()
  )

  /**
    * Starts and binds server
    *
    * Dumps system configuration to the log on DEBUG level
    */
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

    val routes = services.map(_.routes).reduce(_ ~ _)

    Http().bindAndHandle(routes, host, port).onComplete {
      case Success(binding) =>
        log.info(s"Server started at ${binding.localAddress}")
      case Failure(exception) =>
        log.error(exception, "Failed to start server at {}:{}", host, port)
        system.terminate()
    }
  }
}
