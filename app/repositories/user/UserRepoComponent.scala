package repositories.user

import models.User

import scala.concurrent.Future

/**
  * Created by admin on 3/18/16.
  */
trait UserRepoComponent {

  val userRepo: UserRepo

  trait UserRepo {

    def getUser(id: String): Future[Option[User]]

    def setUser(user: User): Future[Boolean]
  }
}
