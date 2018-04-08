package pcs.server.route

import pcs.server.UnitSpec

class HealthRouteSpec extends UnitSpec {

  private val routes = new HealthRoute().routes

  "A health check" should "return 200" in {
    Get("/v1/health") ~> routes ~> check {
      status.intValue() shouldBe 200
    }
  }
}
