package pcs.server.service

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

/**
  * Defines REST API for product catalogues
  */
case class CataloguesService(system: ActorSystem) extends Service {

  lazy val log = Logging(system, classOf[CataloguesService])

  override lazy val routes: Route = findById

  def findById: Route = {
    get {
      pathPrefix("v1" / "products" / LongNumber ) { id =>
        pathEndOrSingleSlash {
          log.info(s"Find product by $id")
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"PCS findProduct by id $id"))
        }
      }
    }
  }

}

