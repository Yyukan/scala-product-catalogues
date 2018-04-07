package pcs.server.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.swagger.annotations.{Api, ApiOperation, ApiResponse, ApiResponses}
import javax.ws.rs.Path
import pcs.server.JsonSupport
import pcs.server.service.CataloguesService
import pcs.core.model.Product

import scala.concurrent.ExecutionContext

/**
  * Defines REST for products in catalogues
  */
@Api(value = "products")
@Path("/v1/products")
class CataloguesRoute(cataloguesService: CataloguesService)
                     (implicit executionContext: ExecutionContext)
  extends Routes with JsonSupport {

  import cataloguesService._

  override lazy val routes: Route = find() ~ all ~ create ~ update ~ remove
  override lazy val path: PathMatcher[Unit] = "v1" / "products"

  @ApiOperation(httpMethod = "GET", value = "Find product by ID")
  def find(): Route = {
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

  @ApiOperation(httpMethod = "GET", value = "Find products")
  @Path("/all")
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

  @ApiOperation(httpMethod = "POST", value = "Create product")
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

  @ApiOperation(httpMethod = "PUT", value = "Update product")
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

  @ApiOperation(httpMethod = "DELETE", value = "Delete product")
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
