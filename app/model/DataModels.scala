package model

case class User(id: Int, travelNickname: String, hometown: String, age: Int, numberOfCountriesVisited: Int)

case class Trip(id: Int, name: String, description: String, userId: Int)
case class TripPlace(id: Int, name: String, tripId: Int)

case class Bucket(id: Int, name: String, tripPlaceId: Int)
case class BucketItem(id: Int, bucketId: Int, itemName: String, itemSharableLink: String, itemNote: String)


object Database {

  var trips: Seq[Trip] = Seq.empty[Trip]

  var tripPlaces: Seq[TripPlace] = Seq.empty[TripPlace]

  var buckets: Seq[Bucket] = Seq.empty[Bucket]

  var bucketItems: Seq[BucketItem] = Seq.empty[BucketItem]

}