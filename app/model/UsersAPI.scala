package model

import scala.util.Try

trait UsersAPI {

  def userExists(authOUserId: String): Boolean

  def register(email: String, fullName: String, auth0Id: String): User

  def getByAuth0Id(auth0Id: String): Option[User]

}

object Users {

  var currentId = 0

  def userExists(authOUserId: String): Boolean =
    Database.users.exists(_.auth0Id == authOUserId)

  def register(fullName: String, auth0Id: String): User = {
    currentId = currentId + 1

    val newUser = User(
      id = currentId,
      auth0Id = auth0Id,
      username = fullName,
      password = auth0Id.split('|').head,
      email = fullName + "@gmail.com" )

    Database.users = Database.users ++ Seq(newUser)
    Database.users.foreach(user => println(user.toString))
    newUser
  }

  def getByAuth0Id(auth0Id: String): Option[User] =
    Database.users.find(_.auth0Id == auth0Id)

}
