package pcs.server

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import pcs.core.model.Product
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
  * Provides JSON support for the REST API
  */
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val productFormat: RootJsonFormat[Product] = jsonFormat2(Product)
}