package repositories.social

import models.Like
import repositories.{BaseRepo, ConvertRepo}

import scala.concurrent.Future

/**
  * Created by admin on 3/21/16.
  */
trait SocialRepoComponentImpl extends SocialRepoComponent with ConvertRepo with BaseRepo {

  val socialRepo = new SocialRepoCouch

  class SocialRepoCouch extends SocialRepo {

    override def like(like: Like): Future[Boolean] = {
      Future.successful(true)
    }
  }

}
