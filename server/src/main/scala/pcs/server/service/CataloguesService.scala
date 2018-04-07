package pcs.server.service

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._

/**
  * Defines REST API for product catalogues
  */
class CataloguesService(implicit system: ActorSystem) extends Service {

  lazy val log = Logging(system, classOf[CataloguesService])

  override lazy val routes: Route = findById

  implicit val timeout: Timeout = 5 seconds

  def findById: Route = {
    get {
      pathPrefix("v1" / "products" / LongNumber ) { id =>
        pathEndOrSingleSlash {
          val remote =
            system.actorSelection("akka.tcp://server-system@127.0.0.1:2551/user/clusterListener")

          println(s"Actor in selection $remote")

          onSuccess((remote ? "Give me any product id").mapTo[Int]) { id =>
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Product id is $id"))
          }
        }
      }
    }
  }

}

