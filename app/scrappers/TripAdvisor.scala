package scrappers

class TripAdvisor(url: String) extends BaseWebScrape (url){

  def tourDuration: String = {
    thisDocument.body().getElementsByAttribute("data-tab")
      .get(3).getAllElements.get(1).getElementsByTag("span").get(1).text()
  }

  def tourDeparturePoint: String = {
    thisDocument.body().getElementsByAttribute("data-tab")
      .get(3).getAllElements.get(1).getElementsByTag("span").get(0).text()
  }

  def tourReturnDetails: String = {
    thisDocument.body().getElementsByAttribute("data-tab")
      .get(3).getAllElements.get(1).getElementsByTag("span").get(2).text()
  }

  def tourPrice: String =
    thisDocument.body().getElementsByAttributeValueContaining("class", "mainPrice").text()

}

object TripAdvisor {

  def apply(url: String): TripAdvisor =
    new TripAdvisor(url)
}



object TripAdvisorTest extends App {
  val links = Seq(
    "https://www.tripadvisor.com/AttractionProductReview-g295371-d11449095-Dubrovnik_Ancient_City_Walls_Wars_2hour_Walking_Tour-Dubrovnik_Dubrovnik_Neretva_C.html",
    "https://www.tripadvisor.com/AttractionProductReview-g295371-d17402922-Game_of_Thrones_and_the_Old_City_Tour-Dubrovnik_Dubrovnik_Neretva_County_Dalmatia.html",
    "https://www.tripadvisor.com/AttractionProductReview-g295371-d11454174-Dubrovnik_Sea_Kayak_Tour-Dubrovnik_Dubrovnik_Neretva_County_Dalmatia.html",
    "https://www.tripadvisor.com/AttractionProductReview-g295371-d13282592-Dubrovnik_Cable_Car_Round_Trip_Ticket-Dubrovnik_Dubrovnik_Neretva_County_Dalmatia.html",
    "https://www.tripadvisor.com/AttractionProductReview-g295371-d11452045-Montenegro_Full_Day_Trip_from_Dubrovnik-Dubrovnik_Dubrovnik_Neretva_County_Dalmati.html#REVIEWS",
    "https://www.tripadvisor.com/Attraction_Review-g295371-d522915-Reviews-Walls_of_Dubrovnik-Dubrovnik_Dubrovnik_Neretva_County_Dalmatia.html",
    "https://www.tripadvisor.com/AttractionProductReview-g295371-d14019370-Game_of_Thrones_Extended_Tour-Dubrovnik_Dubrovnik_Neretva_County_Dalmatia.html"
  )

  links foreach( link => {
    val tripAdvisor = TripAdvisor(link)
    println(tripAdvisor.title)
    println(tripAdvisor.images)
  })
}


//"https://media-cdn.tripadvisor.com/media/photo-w/18/0a/c2/3e/photo2jpg.jpg"