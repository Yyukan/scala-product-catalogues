package pcs.server.service

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

/**
  * Defines REST API for product catalogues
  */
case class CataloguesService() extends Service {

  override lazy val routes: Route = findById

  def findById: Route = {
    get {
      pathPrefix("v1" / "products" / LongNumber ) { id =>
        pathEndOrSingleSlash {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"PCS findProduct by id $id"))
        }
      }
    }
  }

}

