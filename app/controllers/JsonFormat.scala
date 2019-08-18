package controllers

import model.{Bucket, BucketItem, Trip, TripPlace, UserTrips}
import play.api.libs.json.Json


object JsonFormat {
  implicit val bucketFormat = Json.format[Bucket]
  implicit val bucketItemFormat = Json.format[BucketItem]
  implicit val tripFormat = Json.format[Trip]
  implicit val tripPlaceFormat = Json.format[TripPlace]
  implicit val userTripFormat = Json.format[UserTrips]
}
