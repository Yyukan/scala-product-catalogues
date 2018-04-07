package pcs.server.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, Route}
import pcs.server.JsonSupport
import pcs.server.service.CataloguesService
import pcs.core.model.Product

import scala.concurrent.ExecutionContext

/**
  * Defines REST for products in catalogues
  */
class CataloguesRoute(cataloguesService: CataloguesService)
                     (implicit executionContext: ExecutionContext)
  extends Routes with JsonSupport {

  import cataloguesService._

  override lazy val routes: Route = find ~ all ~ create ~ update ~ remove
  override lazy val path: PathMatcher[Unit] = "v1" / "products"

  /**
    * TODO:swagger annotations
    */
  def find: Route = {
    get {
      pathPrefix(path / LongNumber) { id =>
        pathEndOrSingleSlash {
          complete {
            findProductById(id)
          }
        }
      }
    }
  }

  def all: Route = {
    get {
      pathPrefix(path / "all" / IntNumber) { limit =>
        pathEndOrSingleSlash {
          complete {
            findProducts(limit)
          }
        }
      }
    }
  }

  def create: Route = {
    post {
      pathPrefix(path)
        parameters('title.as[String]) { title =>
          complete {
            createProduct(title)
          }
      }
    }
  }

  def update: Route = {
    put {
      pathPrefix(path)
        entity(as[Product]) { product =>
          complete {
            updateProduct(product)
          }
        }
    }
  }

  def remove: Route = {
    delete {
      pathPrefix(path / LongNumber) { id =>
        pathEndOrSingleSlash {
          complete {
            deleteProduct(id)
          }
        }
      }
    }
  }
}
