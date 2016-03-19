package repositories

import models.{ModelJsonFormat, User}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by admin on 3/18/16.
  */
trait UserRepoComponentImpl extends UserRepoComponent with BaseRepo {

  val userRepo = new UserRepoCouch

  class UserRepoCouch extends UserRepo with ModelJsonFormat {

    override def getUser(id: String): Future[Option[User]] = {
      userBucket.get[User](id)
    }
  }

}
