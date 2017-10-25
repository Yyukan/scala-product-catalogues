package pcs.server

import akka.actor.{ActorLogging, ActorSystem}
import akka.event.LoggingAdapter
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigRenderOptions

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

/**
  * Starts and binds server
  */
trait ServerSupport {

  implicit val system: ActorSystem = ActorSystem("pcs-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val log: LoggingAdapter = system.log

  def start(): Unit = {
    val config = system.settings.config

    val renderOpts = ConfigRenderOptions
      .defaults()
      .setOriginComments(false)
      .setComments(true)
      .setFormatted(true)
      .setJson(false)

    log.debug("Application configuration {}", config.root().render(renderOpts))

    val host = config.getString("pcs.server.host")
    val port = config.getInt("pcs.server.port")

    // TODO:oshtykhno move to service
    val api =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "PCS catalogus will be here!"))
        }
      }

    Http().bindAndHandle(api, host, port).onComplete {
      case Success(binding) =>
        log.info(s"Server started at ${binding.localAddress} ")
      case Failure(exception) =>
        log.error("Failed to start server at {}:{}", host, port)
        system.terminate()
    }
  }
}
