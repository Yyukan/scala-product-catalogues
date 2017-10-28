package pcs.server

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.stream.ActorMaterializer
import com.typesafe.config.{Config, ConfigRenderOptions}

import scala.concurrent.ExecutionContextExecutor

/**
  * Provides actor system and it's configuration
  */
trait SystemSupport {

  implicit val system: ActorSystem = ActorSystem("server-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  lazy val log: LoggingAdapter = system.log

  /**
    * Returns actor system configuration
    */
  def config: Config = system.settings.config

  /**
    * Returns config formatting options
    */
  def configRenderOptions: ConfigRenderOptions = ConfigRenderOptions
    .defaults()
    .setOriginComments(false)
    .setComments(true)
    .setFormatted(true)
    .setJson(false)

  /**
    * Returns formatted config
    */
  def configAsString: String = config
    .root()
    .render(configRenderOptions)
}
