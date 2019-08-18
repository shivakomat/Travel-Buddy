package model

trait BucketItemsAPI {

  def add(item: BucketItem): Unit

  def all(): Seq[BucketItem]

  def delete(id: Int): Unit

  def byId(itemId: Int): Option[BucketItem]

}


object BucketItems extends BucketItemsAPI {

  def byBucketId(bucketId: Int): Seq[BucketItem] =
    Database.bucketItems.filter(_.bucketId == bucketId)

  override def add(bucketItem: BucketItem): Unit =
    Database.bucketItems = Database.bucketItems ++ Seq(bucketItem)

  override def delete(id: Int): Unit =
    Database.bucketItems =  Database.bucketItems.filterNot(_.id == id)

  override def all(): Seq[BucketItem] = Database.bucketItems

  override def byId(bucketItemId: Int): Option[BucketItem] =
    Database.bucketItems.find(_.id == bucketItemId)

}
