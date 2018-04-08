package pcs.server.route

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import pcs.core.model.Product
import pcs.server.service.CataloguesService
import pcs.server.{JsonSupport, UnitSpec}

import scala.concurrent.Future

class CataloguesRouteSpec extends UnitSpec with MockitoSugar with JsonSupport {

  private val service = mock[CataloguesService]
  private val routes = new CataloguesRoute(service).routes

  "find product" should "return product by id" in {
    val product = Product(1, "Title")

    when(service.findProductById(1)).thenReturn(Future(Some(product)))

    Get("/v1/products/1") ~> routes ~> check {
      responseAs[Product] shouldBe product
      status.intValue() shouldBe 200
    }
  }

  "find product" should "return nothing" in {
    when(service.findProductById(0)).thenReturn(Future(None))

    Get("/v1/products/0") ~> routes ~> check {
      responseAs[String] shouldBe ""
      status.intValue() shouldBe 200
    }
  }

}
