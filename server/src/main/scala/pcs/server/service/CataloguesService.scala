package pcs.server.service

import pcs.core.model.Product

import scala.concurrent.{ExecutionContext, Future}

/**
  * Defines API for product catalogues
  */
class CataloguesService(implicit executionContext: ExecutionContext) {

  def findProductById(id: Long): Future[Option[Product]] = {
    Future(Some(Product(1L, "Google Chromecast")))
  }

  def findProducts(limit: Int): Future[Seq[Product]] = {
    Future(List(Product(1L, "Google Chromecast")))
  }

  def createProduct(title: String): Future[Product] = {
    Future(Product(1L, "Google Chromecast"))
  }

  def updateProduct(product: Product): Future[Option[Product]] = {
    Future(Some(Product(1L, "Google Chromecast")))
  }

  def deleteProduct(id: Long): Future[Option[Product]] = {
    Future(Some(Product(1L, "Google Chromecast")))
  }
}

