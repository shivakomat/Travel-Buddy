package model

case class User(id: Int, username: String, password: String, email: String,
                travelNickname: String, hometown: String, birthDate: Int, numberOfCountriesVisited: Int)

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



//{ "id": 1, "name": "Toronto October Trip", "description": "Parents visit", "userId": 1 }
//{ "id": 2, "name": "Euro 2019", "description": "My big vacation", "userId": 1 }
//{ "id": 3, "name": "Montreal Trip Christmas", "description": "My Christmas Trip", "userId": 1 }

//{ "id": 1, "name": "Montreal", "tripId": 3}

//{ "id": 4, "name": "Montreal Trip Christmas", "description": "My Christmas Trip", "userId": 1 }