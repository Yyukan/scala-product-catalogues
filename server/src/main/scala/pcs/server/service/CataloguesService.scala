package pcs.server.service

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path}

trait CataloguesService {

  // TODO:oshtykhno move to service
  val api =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "PCS catalogus will be here!"))
      }
    }

}
