package pcs.server.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.swagger.annotations._
import javax.ws.rs.Path
import pcs.server.JsonSupport
import pcs.server.service.CataloguesService
import pcs.core.model.Product

import scala.concurrent.ExecutionContext

/**
  * Defines REST for products in catalogues
  */
@Api(value = "products", produces = "application/json")
@Path("/v1/products")
class CataloguesRoute(cataloguesService: CataloguesService)
                     (implicit executionContext: ExecutionContext)
  extends Routes with JsonSupport {

  import cataloguesService._

  override lazy val routes: Route = find() ~ all ~ create ~ update ~ remove
  override lazy val path: PathMatcher[Unit] = "v1" / "products"

  @ApiOperation(httpMethod = "GET", value = "Find product", response = classOf[Product])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "id", required = true, dataType = "integer", paramType = "path", value = "Product ID")
  ))
  @Path("/{id}")
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

  @ApiOperation(httpMethod = "GET", value = "List products")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "limit", required = true, dataType = "integer", paramType = "path", value = "Limit")
  ))
  @Path("/all/{limit}")
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

  @ApiOperation(httpMethod = "POST", value = "Create product", response = classOf[Product])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "title", required = true, dataType = "string", paramType = "query", value = "Title")
  ))
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

  @ApiOperation(httpMethod = "PUT", value = "Update product", response = classOf[Product])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "product", dataTypeClass = classOf[Product], required = true, paramType = "body", value = "Product")
  ))
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

  @ApiOperation(httpMethod = "DELETE", value = "Delete product", response = classOf[Product])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "id", required = true, dataType = "integer", paramType = "path", value = "Product ID")
  ))
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
