package pcs.server.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.swagger.annotations._
import javax.ws.rs.Path
import pcs.core.model.Product
import pcs.server.JsonSupport
import pcs.server.service.CataloguesService

import scala.concurrent.ExecutionContext

/**
  * Defines REST endpoints for products in catalogues
  */
@Api(value = "products", produces = "application/json")
@Path("/v1/products")
class CataloguesRoute(cataloguesService: CataloguesService)
                     (implicit executionContext: ExecutionContext)
  extends Routes with JsonSupport {

  override lazy val routes: Route = find ~ all ~ create ~ update ~ remove ~ stream

  import cataloguesService._

  @ApiOperation(httpMethod = "GET", value = "Find product", response = classOf[Product])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "id", required = true, dataType = "integer", paramType = "path", value = "Product ID")
  ))
  @Path("/{id}")
  def find: Route = {
    get {
      path("v1" / "products" / LongNumber) { id =>
        rejectEmptyResponse {
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
      path("v1" / "products" / "all" / IntNumber) { limit =>
        complete {
          findProducts(limit)
        }
      }
    }
  }

  @ApiOperation(httpMethod = "GET", value = "Stream products")
  @Path("/stream")
  def stream: Route = {
    path("v1" / "products" / "stream") {
      complete {
        streamProducts()
      }
    }
  }

  @ApiOperation(httpMethod = "POST", value = "Create product", response = classOf[Product])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "title", required = true, dataType = "string", paramType = "query", value = "Title")
  ))
  def create: Route = {
    post {
      path("v1" / "products") {
        parameters('title.as[String]) { title =>
          complete {
            createProduct(title)
          }
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
      path("v1" / "products") {
        entity(as[Product]) { product =>
          complete {
            updateProduct(product)
          }
        }
      }
    }
  }

  @ApiOperation(httpMethod = "DELETE", value = "Delete product", response = classOf[Product])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "id", required = true, dataType = "integer", paramType = "path", value = "Product ID")
  ))
  @Path("/{id}")
  def remove: Route = {
    delete {
      path("v1" / "products" / LongNumber) { id =>
        complete {
          deleteProduct(id)
        }
      }
    }
  }
}
