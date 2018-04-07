package pcs.server.route

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, Route}

/**
  * Defines API for server health check
  */
class HealthRoute extends Routes {

  override lazy val routes: Route = healthCheck
  override lazy val path: PathMatcher[Unit] = "v1" / "health"

  /**
    * TODO add swagger annotations
    */
  def healthCheck: Route = {
    get {
      pathPrefix(path) {
          complete(OK)
      }
    }
  }
}
