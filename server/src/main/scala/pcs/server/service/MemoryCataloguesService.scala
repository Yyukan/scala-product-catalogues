package pcs.server.service

import java.util.concurrent.atomic.AtomicLong

import akka.NotUsed
import akka.stream.scaladsl.Source
import pcs.core.model.Product

import scala.collection.concurrent.TrieMap
import scala.collection.{Seq, concurrent}
import scala.concurrent.{ExecutionContext, Future}

/**
  * Local memory catalogues storage
  */
class MemoryCataloguesService(implicit executionContext: ExecutionContext) extends CataloguesService {

  val sequence = new AtomicLong()
  val storage: concurrent.Map[Long, Product] = TrieMap()

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

  def streamProducts(): Source[Product, NotUsed] = {
    Source.fromIterator(() => storage.values.toIterator)
  }
}
