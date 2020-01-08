package model

import controllers.TripFormData

trait TripsAPI {

  def byUserId(userId: Int): Seq[Trip]

  def add(item: TripFormData): Unit

  def add(item: Trip): Unit

  def all(): Seq[Trip]

  def delete(id: Int): Unit

  def byId(itemId: Int): Option[Trip]

}


object Trips extends TripsAPI {

  var currentId = 0

  override def byUserId(userId: Int): Seq[Trip] =
    all filter(_.userId == userId)

  override def add(trip: Trip): Unit =
    Database.trips = Database.trips ++ Seq(trip)

  override def add(newTrip: TripFormData): Unit = {
    currentId = currentId + 1
    val trip = Trip(id = currentId,
         name = newTrip.name,
         description = newTrip.description,
         budget = newTrip.budget,
         startDate = newTrip.startDate,
         endDate = newTrip.endDate,
         userId = newTrip.userId)

    val tripPlaces = newTrip.tripPlaces.split(',')
    tripPlaces.foreach(p => TripPlaces.add(name = p, tripId = trip.id))

    // Adding the default buckets

    // TODO: Need to change the add implementation of a Buckets API
    // for now setting static id's

    Buckets.add(Bucket(1, "Flights", trip.id))
    Buckets.add(Bucket(2, "Stays", trip.id))
    Buckets.add(Bucket(3, "Food Places", trip.id))
    Buckets.add(Bucket(4, "Places To Visit", trip.id))

    Database.trips = Database.trips ++ Seq(trip)
  }

  override def delete(id: Int): Unit =
    Database.trips = Database.trips.filterNot(_.id == id)

  override def all(): Seq[Trip] = Database.trips

  override def byId(tripId: Int): Option[Trip] =
    Database.trips.find(_.id == tripId)

}