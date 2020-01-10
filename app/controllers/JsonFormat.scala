package controllers

import model.{Bucket, BucketItem, FlightItem, FoodPlaceItem, PlacesToVisit, StayItem, Trip, TripPlace, User, UserTrips}
import play.api.libs.json.Json


object JsonFormat {
  implicit val bucketFormat = Json.format[Bucket]
  implicit val bucketItemFormat = Json.format[BucketItem]
  implicit val tripFormat = Json.format[Trip]
  implicit val tripPlaceFormat = Json.format[TripPlace]
  implicit val user = Json.format[User]

  implicit val tripFormData = Json.format[TripFormData]

  implicit val flightItem = Json.format[FlightItem]
  implicit val stayItem = Json.format[StayItem]
  implicit val placeToVisitItem = Json.format[PlacesToVisit]
  implicit val foodPlacesItem = Json.format[FoodPlaceItem]

  implicit val userTripFormat = Json.format[UserTrips]

}
