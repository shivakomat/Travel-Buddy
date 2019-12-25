package controllers

import javax.inject.Inject
import play.api.cache._
import play.api.libs.json._
import play.api.mvc._

class ProfileController @Inject() (cache: DefaultSyncCacheApi) extends Controller {

  def AuthenticatedAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action {
      request =>
        println("Inside profile controller here")
        println(request.session.data)
        println("session: " + request.session.get("id"))
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
}

