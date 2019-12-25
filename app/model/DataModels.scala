package model

case class User(id: Int, auth0Id: String, username: String, password: String, email: String,
                travelNickname: String = "", hometown: String = "", birthDate: Int = 0,
                numberOfCountriesVisited: Int = 0)

case class Trip(id: Int, name: String, description: String, budget: Double, startDate: String, endDate: String, userId: Int)
case class TripPlace(id: Int, name: String, tripId: Int)

case class Bucket(id: Int, name: String, tripPlaceId: Int)
case class BucketItem(id: Int, bucketId: Int, itemName: String, itemSharableLink: String,
                      itemNote: String, itemType: String = "", price: Double = 0.00, fromPlace: String = "",
                      toPlace: String = "", hotelType: String = "", cuisineType: String = "", company: String = "")


object Database {

  var trips: Seq[Trip] = Seq.empty[Trip]

  var tripPlaces: Seq[TripPlace] = Seq.empty[TripPlace]

  var buckets: Seq[Bucket] = Seq.empty[Bucket]

  var bucketItems: Seq[BucketItem] = Seq.empty[BucketItem]

  var users: Seq[User] = Seq.empty[User]

}



//{ "id": 1, "name": "Toronto October Trip", "description": "Parents visit", "userId": 1 }
//{ "id": 2, "name": "Euro 2019", "description": "My big vacation", "userId": 1 }
//{ "id": 3, "name": "Montreal Trip Christmas", "description": "My Christmas Trip", "userId": 1 }

//{ "id": 1, "name": "Montreal", "tripId": 3}

//{ "id": 4, "name": "Montreal Trip Christmas", "description": "My Christmas Trip", "userId": 1 }