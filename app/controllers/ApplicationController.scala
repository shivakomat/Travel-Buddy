package controllers

import java.util.UUID.randomUUID

import helpers.{Auth0Config, RandomUtil}
import javax.inject.Inject
import play.api.cache._
import play.api.mvc._
import play.api.Configuration
import controllers.HomeController

class ApplicationController @Inject() (cache: SyncCacheApi, configuration: Configuration) extends Controller {

  private val config = Auth0Config.get(configuration)

  def login: Action[AnyContent] = Action {
    val state = RandomUtil.alphanumeric() // Generate random state parameter
    val id = randomUUID().toString
    cache.set(id + "state", state)
    val query = String.format(
      "authorize?client_id=%s&redirect_uri=%s&response_type=code&scope=openid profile&audience=%s&state=%s",
      "vK4iZYJ5SodMQvzIjRRjLUTfNNZgR7QP",
      "http://localhost:9000/callback",
      "https://shiva-komatreddy.auth0.com/api/v2/",
      state
    )


    Redirect(String.format(
      "https://%s/%s",
      "shiva-komatreddy.auth0.com",
      query)
    ).withSession("id" -> id)
  }

  def logout: Action[AnyContent] = Action { request =>
    val host = request.host
    var scheme = "http"
    if (request.secure) {
      scheme = "https"
    }
    val returnTo = scheme + "://" + host
    Redirect(String.format(
      "https://%s/v2/logout?client_id=%s&returnTo=%s",
      config.domain,
      config.clientId,
      returnTo)
    ).withNewSession
  }

}