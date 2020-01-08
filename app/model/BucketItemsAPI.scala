package model

import controllers.BucketItemForm

trait BucketItemsAPI {

  def add(formData: BucketItemForm): Unit

  def addFlightItems(flightItem: FlightItem): Unit

  def addPlacesToVisitItems(placesToVisit: PlacesToVisit): Unit

  def addFoodPlaceItems(foodPlaceItem: FoodPlaceItem): Unit

  def addStayItems(stayItem: StayItem): Unit

  def add(item: BucketItem): Unit

  def all(): Seq[BucketItem]

  def delete(id: Int): Unit

  def byId(itemId: Int): Option[BucketItem]

}


object BucketItems extends BucketItemsAPI {

  var currentId = 0

  def byBucketId(bucketId: Int): Seq[BucketItem] =
    Database.bucketItems.filter(_.bucketId == bucketId)

  override def add(bucketItem: BucketItem): Unit =
    Database.bucketItems = Database.bucketItems ++ Seq(bucketItem)


  override def addFlightItems(flightItem: FlightItem): Unit = {
    currentId = currentId + 1
    flightItem.copy(itemId = Some(currentId))
    Database.flightItems = Database.flightItems ++ Seq(flightItem)
  }

  override def addPlacesToVisitItems(placesToVisit: PlacesToVisit): Unit = {
    currentId = currentId + 1
    placesToVisit.copy(itemId = currentId)
    Database.placesToVisitItems = Database.placesToVisitItems ++ Seq(placesToVisit)
  }

  override def addFoodPlaceItems(foodPlaceItem: FoodPlaceItem): Unit = {
    currentId = currentId + 1
    foodPlaceItem.copy(itemId = Some(currentId))
    Database.foodPlaceItems = Database.foodPlaceItems ++ Seq(foodPlaceItem)
  }

  override def addStayItems(stayItem: StayItem): Unit = {
    currentId = currentId + 1
    stayItem.copy(itemId = Some(currentId))
    Database.stayItems = Database.stayItems ++ Seq(stayItem)
  }

  override def add(formData: BucketItemForm): Unit = {
    currentId = currentId + 1
    val newBucketItem = BucketItem(id = currentId,
               bucketId = 1,
               itemName = formData.name,
               itemNote = formData.notes,
               itemType = formData.itemType,
               price = formData.cost,
               itemSharableLink = formData.sharableLink)
    Database.bucketItems = Database.bucketItems ++ Seq(newBucketItem)
  }

  override def delete(id: Int): Unit =
    Database.bucketItems =  Database.bucketItems.filterNot(_.id == id)

  override def all(): Seq[BucketItem] = Database.bucketItems

  override def byId(bucketItemId: Int): Option[BucketItem] =
    Database.bucketItems.find(_.id == bucketItemId)

}
