package pcs.server.service

import akka.http.scaladsl.server.Route

/**
  * Service should expose REST API via routes
  */
trait Service {

  /**
    * Returns service route as [[Route]]
    */
  def routes: Route
}
