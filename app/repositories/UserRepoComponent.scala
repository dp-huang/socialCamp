package repositories

import models.User
import org.reactivecouchbase.client.OpResult

import scala.concurrent.Future

/**
  * Created by admin on 3/18/16.
  */
trait UserRepoComponent {

  val userRepo: UserRepo

  trait UserRepo {

    def getUser(id: String): Future[Option[User]]

    def addUser(user: User): Future[Boolean]
  }
}
