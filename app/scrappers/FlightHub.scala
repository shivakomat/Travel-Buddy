package scrappers

import org.jsoup.Jsoup

import scala.util.Try

class FlightHub(url: String) {

  val thisDocument = Jsoup.connect(url).get()

  def leavingFrom: String = {
    thisDocument
      .body()
      .getElementsByAttributeValueContaining("placeholder", "Leaving from")
      .get(0)
      .attr("value")
  }

  def goingTo: String = {
     thisDocument
       .body()
       .getElementsByAttributeValueContaining("placeholder", "Going to")
       .get(0)
       .attr("value")
  }

  def departureDate: String = {
    thisDocument
      .body()
      .getElementsByAttribute("data-locale-departure")
      .get(0)
      .getElementsByAttributeValueContaining("class", "search-calendar")
      .`val`()
  }

  def returnTimeIfApplicable: Option[String] = {
    Try(
      thisDocument
        .body()
        .getElementsByAttribute("data-locale-return")
        .get(0)
        .getElementsByAttributeValueContaining("class", "search-calendar")
        .`val`()
      ).toOption
  }

  def lowestPrice: String = {
    thisDocument
      .body()
      .getElementsByAttributeValueContaining("id", "search-result-lowest-price")
      .attr("data-price")
  }

}


object FlightHub {
  def apply(url: String): FlightHub = new FlightHub(url)
}



object FlightHubTest extends App {

  val links = Seq(
    "https://www.flighthub.com/flight/search?type=roundtrip&seg0_from=SFO&seg0_to=DBV&seg0_date=2020-02-14&seg1_date=2020-02-28&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy&seg1_from=DBV&seg1_to=SFO&campaign=3851&search_id=dac8c372e31b9933f3ad3b40e32e07b1&flexible_search_id=dac8c372e31b9933f3ad3b40e32e07b1",
    "https://www.flighthub.com/flight/search?type=roundtrip&seg0_from=YYZ&seg0_to=SYD&seg0_date=2020-02-14&seg1_date=2020-02-28&seg1_from_code=SYD&seg1_to_code=YYZ&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy&seg1_from=SYD&seg1_to=YYZ&campaign=3851&search_id=11d57fa7d52234e1d2fc9957d4eb525d&flexible_search_id=11d57fa7d52234e1d2fc9957d4eb525d",
    "https://www.flighthub.com/flight/search?type=roundtrip&seg0_from=YYZ&seg0_to=SFO&seg0_date=2020-04-01&seg1_date=2020-04-14&seg1_from_code=SYD&seg1_to_code=YYZ&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy&seg1_from=SFO&seg1_to=YYZ&campaign=3851&search_id=b97056598bbd51681972c4f676e76336&flexible_search_id=b97056598bbd51681972c4f676e76336"

  )

  links foreach { link =>

    val flightHub = FlightHub(link)
    println(flightHub.leavingFrom)
    println(flightHub.goingTo)
    println(flightHub.departureDate)
    println(flightHub.returnTimeIfApplicable)
    println(flightHub.lowestPrice)


  }








}



