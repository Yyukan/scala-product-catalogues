package pcs.server.service

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
  * Defines REST API for product categories
  */
class CategoryService(implicit system: ActorSystem) extends Service {

  lazy val log = Logging(system, classOf[CategoryService])

  override lazy val routes: Route = findById

  def findById: Route = {
    get {
      pathPrefix("v1" / "categories" / LongNumber ) { id =>
        pathEndOrSingleSlash {
          log.info(s"Find category by $id")
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"find category by id $id"))
        }
      }
    }
  }

}
