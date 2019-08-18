package main


trait TripsAPI {

  def byUserId(userId: Int): Seq[Trip]

  def add(item: Trip): Unit

  def all(): Seq[Trip]

  def delete(id: Int): Unit

  def byId(itemId: Int): Option[Trip]

}


object Trips extends TripsAPI {

  override def byUserId(userId: Int): Seq[Trip] =
    all filter(_.userId == userId)

  override def add(trip: Trip): Unit =
    Database.trips = Database.trips ++ Seq(trip)

  override def delete(id: Int): Unit =
    Database.trips = Database.trips.filterNot(_.id == id)

  override def all(): Seq[Trip] = Database.trips

  override def byId(tripId: Int): Option[Trip] =
    Database.trips.find(_.id == tripId)

}