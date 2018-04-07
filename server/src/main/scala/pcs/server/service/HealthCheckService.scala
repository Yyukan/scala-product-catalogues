package pcs.server.service

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
  * Defines API for server health check
  */
class HealthCheckService(implicit system: ActorSystem) extends Service {

  override def routes: Route = healthCheck

  def healthCheck: Route = {
    get {
      pathPrefix("v1" / "healthcheck") {
          complete("OK")
      }
    }
  }
}
