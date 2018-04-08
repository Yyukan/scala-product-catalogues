package pcs.server.route

import akka.http.scaladsl.server.{PathMatcher, Route}

/**
  * Exposes REST API via routes
  */
trait Routes {

  /**
    * Returns service route as [[Route]]
    */
  def routes: Route
}
