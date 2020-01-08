package controllers

import javax.inject.Inject
import model.UserTripsAPI
import play.api.cache._
import play.api.libs.json._
import play.api.mvc._

class ProfileController @Inject() (cache: DefaultSyncCacheApi) extends Controller {

  def AuthenticatedAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action {
      request =>
        request.session.get("id")
          .flatMap(id => Some(cache.get[JsValue](id + "profile")))
          .map(profile => f(request))
          .orElse(Some(Redirect(routes.HomeController.index()))).get
    }
  }

  def getUserId(session: Session): Int = {
    val id = session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    (profile \ "app_user_id").as[Int]
  }

  def profilePage: Action[AnyContent] = AuthenticatedAction { request =>
    val id = request.session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    val userId = (profile \ "app_user_id").as[Int]
    Ok(views.html.userProfile(profile, userId))
  }

  def profileTripsPage: Action[AnyContent] = AuthenticatedAction { request =>
    val id = request.session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    val userId = (profile \ "app_user_id").as[Int]
    val userTrips = UserTripsAPI.byUser(userId)
    Ok(views.html.myTrips(userTrips.trips))
  }

  def angularProfileTripsPage: Action[AnyContent] = AuthenticatedAction { request =>
    val id = request.session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    val userId = (profile \ "app_user_id").as[Int]
    Ok(views.html.profileTrips(userId))
  }

  def createTripPage: Action[AnyContent] = AuthenticatedAction { request =>
    val id = request.session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    val userId = (profile \ "app_user_id").as[Int]
    println("create trip page : " + userId)
    Ok(views.html.createNewTrip(userId))
  }

  def tripPage(tripId: Int): Action[AnyContent] = AuthenticatedAction { request =>
    val id = request.session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    val userId = (profile \ "app_user_id").as[Int]
    Ok(views.html.trip(userId, tripId))
  }

  def createFoodPlaceItemPage(tripId: Int): Action[AnyContent] = AuthenticatedAction { request =>
    Ok(views.html.foodPlaceItem(getUserId(request.session), tripId))
  }

  def createStayItemPage(tripId: Int): Action[AnyContent] = AuthenticatedAction { request =>
    Ok(views.html.stayItem(getUserId(request.session), tripId))
  }

  def createPlacesToVisitItemPage(tripId: Int): Action[AnyContent] = AuthenticatedAction { request =>
    Ok(views.html.placesToVisitItem(getUserId(request.session), tripId))
  }

  def createFlightItemPage(tripId: Int): Action[AnyContent] = AuthenticatedAction { request =>
    Ok(views.html.flightItem(getUserId(request.session), tripId))
  }


}

