package pcs.server.service

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
  * Defines REST API for product categories
  */
case class CategoryService() extends Service {

  override def routes = findById

  def findById: Route = {
    get {
      pathPrefix("v1" / "categories" / LongNumber ) { id =>
        pathEndOrSingleSlash {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"find category by id $id"))
        }
      }
    }
  }

}
