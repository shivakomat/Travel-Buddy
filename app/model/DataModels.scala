package model

case class User(id: Int, auth0Id: String, username: String, password: String, email: String,
                travelNickname: String = "", hometown: String = "", birthDate: Int = 0,
                numberOfCountriesVisited: Int = 0)

case class Trip(id: Int, name: String, description: String, budget: Double, startDate: String, endDate: String, userId: Int)
case class TripPlace(id: Int, name: String, tripId: Int)

case class Bucket(id: Int, name: String, tripPlaceId: Int = 0, tripId: Int = 0) //need to remove trip place id here
case class BucketItem(id: Int, bucketId: Int, itemName: String, itemSharableLink: String,
                      itemNote: String, itemType: String = "", price: Double = 0.00, fromPlace: String = "",
                      toPlace: String = "", hotelType: String = "", cuisineType: String = "", company: String = "")


case class FlightItem(itemId: Option[Int] = None, itemName: String,
                      from: String, to: String,
                      estimatedCost: Double, notes: Option[String],
                      shareableLink: Option[String], airlines: String,
                      tripId: Int, userId: Int)

case class StayItem(itemId: Option[Int] = None, name: String, costPerNight: Double, reviewStars: Int,
                    specificLocation: Option[String], numberOfDaysToStay: Int, notes: Option[String],
                    shareableLink: String, tripPlaceId: Int, tripId: Int, userId: Int)

case class FoodPlaceItem (itemId: Option[Int] = None, placeName: String, estimatedPrice :Option[Double], cuisineType: Option[String],
                          reviewStars: Int, appetiteType: Option[String], reservationRequired: Option[Boolean],
                          phoneNumber: Option[Long], notes: Option[String], shareableLink: String,
                          tripPlaceId: Int, tripId: Int, userId: Int)

case class PlacesToVisit (itemId: Option[Int] = None, placeName: String, mapLink: Option[String],
                          anyFees: Option[Double], notes: Option[String], shareableLink: Option[String],
                          tripPlaceId: Int, tripId: Int, userId: Int)


case class ThingToDo (itemId: Int, name: String, estimatedCost: String, notes: String, sharableLink: String,
                      tripPlaceId: Int, tripId: Int, userId: Int)


object Database {

  var trips: Seq[Trip] = Seq.empty[Trip]

  var tripPlaces: Seq[TripPlace] = Seq.empty[TripPlace]

  var buckets: Seq[Bucket] = Seq.empty[Bucket]

  var bucketItems: Seq[BucketItem] = Seq.empty[BucketItem]

  var users: Seq[User] = Seq.empty[User]

  var flightItems: Seq[FlightItem] = Seq.empty[FlightItem]

  var stayItems: Seq[StayItem] = Seq.empty[StayItem]

  var placesToVisitItems: Seq[PlacesToVisit] = Seq.empty[PlacesToVisit]

  var foodPlaceItems: Seq[FoodPlaceItem] = Seq.empty[FoodPlaceItem]

  var thingsToDoItems: Seq[ThingToDo] = Seq.empty[ThingToDo]

}



//{ "id": 1, "name": "Toronto October Trip", "description": "Parents visit", "userId": 1 }
//{ "id": 2, "name": "Euro 2019", "description": "My big vacation", "userId": 1 }
//{ "id": 3, "name": "Montreal Trip Christmas", "description": "My Christmas Trip", "userId": 1 }

//{ "id": 1, "name": "Montreal", "tripId": 3}

//{ "id": 4, "name": "Montreal Trip Christmas", "description": "My Christmas Trip", "userId": 1 }