package pcs.server.route

import akka.http.scaladsl.server.Route
import com.github.swagger.akka.model.Info
import com.github.swagger.akka.{SwaggerHttpService, SwaggerSite}

/**
  * Defines Swagger support
  */
class SwaggerRoute extends SwaggerHttpService with SwaggerSite with Routes {
  override val apiClasses: Set[Class[_]] = Set(classOf[HealthRoute], classOf[CataloguesRoute])
  override val info = Info(description = "Product Catalogues REST API")

  override lazy val routes: Route = swaggerSiteRoute ~ super.routes
}
