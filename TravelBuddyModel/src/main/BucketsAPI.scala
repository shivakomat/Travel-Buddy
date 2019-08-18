package main

trait BucketsAPI {

  def add(bucket: Bucket): Unit

  def all(): Seq[Bucket]

  def delete(id: Int): Unit

  def byId(bucketId: Int): Option[Bucket]
}


object Buckets extends BucketsAPI {

  def byTripPlace(tripPlaceId: Int): Seq[Bucket] =
    all filter(_.tripPlaceId == tripPlaceId)

  override def add(bucket: Bucket): Unit =
    Database.buckets = Database.buckets ++ Seq(bucket)

  override def delete(id: Int): Unit =
    Database.buckets = Database.buckets.filterNot(_.id == id)

  override def all(): Seq[Bucket] = Database.buckets

  override def byId(bucketId: Int): Option[Bucket] =
    Database.buckets.find(_.id == bucketId)

}
