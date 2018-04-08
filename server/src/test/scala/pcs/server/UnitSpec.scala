package pcs.server

import org.scalatest.{Matchers, FlatSpec}
import akka.http.scaladsl.testkit.ScalatestRouteTest

/**
  * Base spec for unit tests
  */
trait UnitSpec extends FlatSpec with Matchers with ScalatestRouteTest {

}
