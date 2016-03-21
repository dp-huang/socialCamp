package repositories.social

import models.Like

import scala.concurrent.Future

/**
  * Created by admin on 3/21/16.
  */
trait SocialRepoComponent {

  val socialRepo: SocialRepo

  trait SocialRepo {

    def like(like: Like): Future[Boolean]

  }
}
