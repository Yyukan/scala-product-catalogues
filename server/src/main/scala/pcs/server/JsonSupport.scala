package pcs.server

import akka.http.scaladsl.common.{EntityStreamingSupport, JsonEntityStreamingSupport}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import pcs.core.model.Product
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
  * Provides JSON support for the REST API
  */
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  /**
    * Formats
    */
  implicit val productFormat: RootJsonFormat[Product] = jsonFormat2(Product)
  
  /**
    * Streaming support
    */
  implicit val jsonStreamingSupport: JsonEntityStreamingSupport = EntityStreamingSupport.json()
}