package controllers

case class TripFormData(name: String,
                    description: String,
                    tripPlaces: String,
                    budget: Int,
                    startDate: String,
                    endDate: String,
                    userId: Int)

case class BucketItemForm(bucketId: Int,
                          name: String,
                          sharableLink: String,
                          notes: String = "",
                          itemType: String = "",
                          cost: Double = 0.00,
                          tripPlace: String)
