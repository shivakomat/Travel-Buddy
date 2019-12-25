package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import javax.inject.Inject
import play.api.cache._
import play.api.http.HeaderNames
import play.api.http.MimeTypes
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.libs.ws._
import play.api.mvc.{Action, AnyContent, Controller}
import helpers.Auth0Config
import play.api.Configuration
import play.api.cache.SyncCacheApi

class CallbackController @Inject() (cache: DefaultSyncCacheApi, ws: WSClient, configuration: Configuration) extends Controller {

  private val config = Auth0Config.get(configuration)

  def callback(codeOpt: Option[String] = None, stateOpt: Option[String] = None): Action[AnyContent] = Action.async { request =>
    val sessionId = request.session.get("id").get
    println("Inside call back controller ---")
    println("sessionId = " + sessionId)
    if (stateOpt == cache.get(sessionId + "state")) {
      (for {
        code <- codeOpt
      } yield {
        getToken(code, sessionId).flatMap { case (idToken, accessToken) =>
          getUser(accessToken).map { user =>
            println("user :" +  user.toString())
            println("session id: " + request.session.get("id").get)
            val id = request.session.get("id").get
            cache.set(request.session.get("id").get + "profile", user)
            println("profile: " + cache.get(id + "profile").toString)
            Redirect(routes.ProfileController.profilePage())
              .withSession(
                "idToken" -> idToken,
                "accessToken" -> accessToken,
                "id" -> id // TODO need to check if this is a safe way, passing the session id
              )
          }

        }.recover {
          case ex: IllegalStateException => Unauthorized(ex.getMessage)
        }
      }).getOrElse(Future.successful(BadRequest("No parameters supplied")))
    } else {
      Future.successful(BadRequest("Invalid state parameter"))
    }
  }

  def getToken(code: String, sessionId: String): Future[(String, String)] = {
    val tokenResponse = ws.url(String.format("https://%s/oauth/token", "shiva-komatreddy.auth0.com")).
      withHeaders(HeaderNames.ACCEPT -> MimeTypes.JSON).
      post(
        Json.obj(
          "client_id" -> config.clientId,
          "client_secret" -> config.secret,
          "redirect_uri" -> config.callbackURL,
          "code" -> code,
          "grant_type"-> "authorization_code",
          "audience" -> config.audience
        )
      )

    tokenResponse.flatMap { response =>
      (for {
        idToken <- (response.json \ "id_token").asOpt[String]
        accessToken <- (response.json \ "access_token").asOpt[String]
      } yield {
        cache.set(sessionId + "id_token", idToken)
        cache.set(sessionId + "access_token", accessToken)
        Future.successful((idToken, accessToken))
      }).getOrElse(Future.failed[(String, String)](new IllegalStateException("Tokens not sent")))
    }

  }

  def getUser(accessToken: String): Future[JsValue] = {
    val userResponse = ws.url(String.format("https://%s/userinfo", config.domain))
      .withQueryString("access_token" -> accessToken)
      .get()

    userResponse.flatMap(response => Future.successful(response.json))
  }
}