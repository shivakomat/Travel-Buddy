package controllers

case class TripFormData(name: String,
                    description: String,
                    tripPlaces: String,
                    budget: Int,
                    startDate: String,
                    endDate: String,
                    userId: Int)
