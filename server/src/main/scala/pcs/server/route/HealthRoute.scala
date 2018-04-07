package pcs.server.route

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.swagger.annotations.{Api, ApiOperation, ApiResponse, ApiResponses}
import javax.ws.rs.Path

/**
  * Defines API for server health check
  */
@Api(value = "health")
@Path("/v1/health")
class HealthRoute extends Routes {

  override lazy val routes: Route = healthCheck
  override lazy val path: PathMatcher[Unit] = "v1" / "health"

  @ApiOperation(httpMethod = "GET", value = "Health Check Endpoint")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Health Check OK"))
  )
  def healthCheck: Route = {
    get {
      pathPrefix(path) {
          complete(OK)
      }
    }
  }
}
