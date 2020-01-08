package model

case class UserTrips(userId: Int, trips: Seq[Trip],
                     tripPlaces: Seq[TripPlace],
                     buckets: Seq[Bucket], bucketItems: Seq[BucketItem])

object UserTripsAPI {

  Trips
    .add(Trip(id = 1, name = "Euro Trip 2019", userId = 1, description = "My 2019 Euro Trip", startDate = "", endDate = "", budget = 5000))

  Trips
    .add(Trip(id = 2, name = "Bali Trip", userId = 1, description = "Bali trip with co worker", startDate = "", endDate = "", budget = 3000))

  TripPlaces
    .add(TripPlace(id = 1, name = "Barcelona", tripId = 1))
  TripPlaces
    .add(TripPlace(id = 2, name = "Dubrovnik", tripId = 1))

  Buckets.add(Bucket(name = "Flights", id = 1, tripPlaceId = 1))
  BucketItems.add(BucketItem(id = 1, bucketId = 1, null, null, null))
  BucketItems.add(BucketItem(id = 2, bucketId = 1, null, null, null))
  BucketItems.add(BucketItem(id = 3, bucketId = 1, null, null, null))


  Buckets.add(Bucket(name = "AirBnb", id = 2, tripPlaceId = 1))
  BucketItems.add(BucketItem(id = 4, bucketId = 2, null, null, null))
  BucketItems.add(BucketItem(id = 5, bucketId = 2, null, null, null))
  BucketItems.add(BucketItem(id = 6, bucketId = 2, null, null, null))



  Buckets.add(Bucket(name = "Trains", id = 3, tripPlaceId = 2))
  BucketItems.add(BucketItem(id = 7, bucketId = 3, null, null, null))
  BucketItems.add(BucketItem(id = 8, bucketId = 3, null, null, null))
  BucketItems.add(BucketItem(id = 9, bucketId = 3, null, null, null))


  Buckets.add(Bucket(name = "Hostels", id = 4, tripPlaceId = 2))
  BucketItems.add(BucketItem(id = 10, bucketId = 4, null, null, null))
  BucketItems.add(BucketItem(id = 11, bucketId = 4, null, null, null))
  BucketItems.add(BucketItem(id = 12, bucketId = 4, null, null, null))

  def byUser(userId: Int): UserTrips = {
    val trips = Trips.byUserId(userId)
    val tripPlaces = trips.flatMap(trip => TripPlaces.byTrip(trip.id))
    val buckets = tripPlaces.flatMap(tripPlace => Buckets.byTripPlace(tripPlace.id))
    val bucketItems = buckets.flatMap(bucket => BucketItems.byBucketId(bucket.id))
    UserTrips(userId, trips, tripPlaces, buckets, bucketItems)
  }

  def createTrip(trip: Trip, tripPlaces: Seq[TripPlace]): UserTrips = {
    Trips add(trip)
    tripPlaces foreach TripPlaces.add
    byUser(trip.userId)
  }


  def createTripPlace(userId: Int, tripPlace: TripPlace): UserTrips = {
    TripPlaces.add(tripPlace)
    byUser(userId)
  }


  def createBucket(userId: Int, newBucket: Bucket): UserTrips = {
    Buckets.add(newBucket)
    byUser(userId)
  }


  def createBucketItem(userId: Int, bucketItem: BucketItem): UserTrips = {
    BucketItems.add(bucketItem)
    byUser(userId)
  }

}


object UserTripsAPITest {

  Trips
    .add(Trip(id = 1, name = "Euro Trip 2019", userId = 1, description = "My 2019 Euro Trip", startDate = "", endDate = "", budget = 5000))

  Trips
    .add(Trip(id = 2, name = "Bali Trip", userId = 1, description = "Bali trip with co worker", startDate = "", endDate = "", budget = 3000))

  TripPlaces
    .add(TripPlace(id = 1, name = "Barcelona", tripId = 1))
  TripPlaces
    .add(TripPlace(id = 2, name = "Dubrovnik", tripId = 1))

  Buckets.add(Bucket(name = "Flights", id = 1, tripPlaceId = 1))
  BucketItems.add(BucketItem(id = 1, bucketId = 1, null, null, null))
  BucketItems.add(BucketItem(id = 2, bucketId = 1, null, null, null))
  BucketItems.add(BucketItem(id = 3, bucketId = 1, null, null, null))


  Buckets.add(Bucket(name = "AirBnb", id = 2, tripPlaceId = 1))
  BucketItems.add(BucketItem(id = 4, bucketId = 2, null, null, null))
  BucketItems.add(BucketItem(id = 5, bucketId = 2, null, null, null))
  BucketItems.add(BucketItem(id = 6, bucketId = 2, null, null, null))



  Buckets.add(Bucket(name = "Trains", id = 3, tripPlaceId = 2))
  BucketItems.add(BucketItem(id = 7, bucketId = 3, null, null, null))
  BucketItems.add(BucketItem(id = 8, bucketId = 3, null, null, null))
  BucketItems.add(BucketItem(id = 9, bucketId = 3, null, null, null))


  Buckets.add(Bucket(name = "Hostels", id = 4, tripPlaceId = 2))
  BucketItems.add(BucketItem(id = 10, bucketId = 4, null, null, null))
  BucketItems.add(BucketItem(id = 11, bucketId = 4, null, null, null))
  BucketItems.add(BucketItem(id = 12, bucketId = 4, null, null, null))

  UserTripsAPI.byUser(1).tripPlaces.foreach(println)
}