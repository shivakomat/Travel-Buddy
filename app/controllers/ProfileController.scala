package controllers

import javax.inject.Inject
import model.{Trips, UserTrips, UserTripsAPI}
import play.api.cache._
import play.api.libs.json._
import play.api.mvc._
import JsonFormat._

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

  def profilePage: Action[AnyContent] = AuthenticatedAction { request =>
    val id = request.session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    Ok(views.html.userProfile(profile))
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
    Ok(views.html.createNewTrip(userId))
  }
}

