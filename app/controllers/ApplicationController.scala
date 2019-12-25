package controllers

import java.math.BigInteger
import java.security.SecureRandom
import java.util.UUID.randomUUID
import helpers.{Auth0Config, RandomUtil}
import javax.inject.Inject
import play.api.cache._
import play.api.mvc._
import play.api.Configuration

class ApplicationController @Inject() (cache: DefaultSyncCacheApi, configuration: Configuration) extends Controller {

  private val config = Auth0Config.get(configuration)

  def login: Action[AnyContent] = Action {
    // Generate random state parameter
    val state = RandomUtil.alphanumeric()

    var audience = config.audience
    if (config.audience == ""){
      audience = String.format("https://%s/userinfo", config.domain)
    }

    val id = randomUUID().toString

    cache.set(id + "state", state)

    val query = String.format(
      "authorize?client_id=%s&redirect_uri=%s&response_type=code&scope=openid profile&audience=%s&state=%s",
      config.clientId,
      config.callbackURL,
      audience,
      state
    )

    Redirect(String.format("https://%s/%s", config.domain, query)).withSession("id" -> id)
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