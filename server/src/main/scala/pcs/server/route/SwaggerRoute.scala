package pcs.server.route

import akka.http.scaladsl.server.PathMatcher
import com.github.swagger.akka.{SwaggerHttpService, SwaggerSite}
import com.github.swagger.akka.model.Info

/**
  * Defines Swagger support
  */
class SwaggerRoute extends SwaggerHttpService with SwaggerSite with Routes {
  override val apiClasses: Set[Class[_]] = Set(classOf[HealthRoute], classOf[CataloguesRoute])
  override val host = "localhost:19999"
  override val info = Info(description = "Product Catalogues REST API")

  override def path: PathMatcher[Unit] = ""

  override lazy val routes = swaggerSiteRoute ~ super.routes
}
