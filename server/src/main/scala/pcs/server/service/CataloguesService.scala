package pcs.server.service

import java.util.concurrent.atomic.AtomicLong

import pcs.core.model.Product

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}

/**
  * Defines API for product catalogues
  */
class CataloguesService(implicit executionContext: ExecutionContext) {

  // TODO:oshtykhno implement remote persistence
  val sequence = new AtomicLong()
  val storage: mutable.Map[Long, Product] = mutable.Map()
  
  def findProductById(id: Long): Future[Option[Product]] = {
    Future(storage.get(id))
  }

  def findProducts(limit: Int): Future[Seq[Product]] = {
    Future(storage.values.toList.take(limit))
  }

  def createProduct(title: String): Future[Product] = {
    Future {
      val id = sequence.getAndAdd(1)
      val product = Product(id, title)
      storage.put(id, product)
      product
    }
  }

  def updateProduct(product: Product): Future[Option[Product]] = {
    Future(storage.put(product.id, product))
  }

  def deleteProduct(id: Long): Future[Option[Product]] = {
    Future(storage.remove(id))
  }
}

