package pcs.server.service

import akka.NotUsed
import akka.stream.scaladsl.Source
import pcs.core.model.Product

import scala.collection._
import scala.concurrent.Future


/**
  * Defines API for product catalogues
  */
trait CataloguesService {

  def findProductById(id: Long): Future[Option[Product]]

  def findProducts(limit: Int): Future[Seq[Product]]

  def createProduct(title: String): Future[Product]

  def updateProduct(product: Product): Future[Option[Product]]

  def deleteProduct(id: Long): Future[Option[Product]]

  def streamProducts(): Source[Product, NotUsed]
}



