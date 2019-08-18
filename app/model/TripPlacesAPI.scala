package model

trait TripPlacesAPI {

  def byTrip(tripId: Int): Seq[TripPlace]

  def add(item: TripPlace): Unit

  def all(): Seq[TripPlace]

  def delete(id: Int): Unit

  def byId(itemId: Int): Option[TripPlace]

}

object TripPlaces extends TripPlacesAPI {

  override def add(tripPlace: TripPlace): Unit =
    Database.tripPlaces = Database.tripPlaces ++ Seq(tripPlace)

  override def delete(id: Int): Unit =
    Database.tripPlaces = Database.tripPlaces.filterNot(_.id == id)

  override def all(): Seq[TripPlace] = Database.tripPlaces

  override def byId(tripPlaceId: Int): Option[TripPlace] =
    Database.tripPlaces.find(_.id == tripPlaceId)

  override def byTrip(tripId: Int): Seq[TripPlace] =
    Database.tripPlaces.filter(_.tripId == tripId)

}
