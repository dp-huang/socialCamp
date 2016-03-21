package services.social

import dtos._
import repositories.social.SocialRepoComponent
import services._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by admin on 3/21/16.
  */
trait SocialServiceComponentImpl extends SocialServiceComponent {
  this: SocialRepoComponent =>
  val socialService = new SocialServiceImpl

  class SocialServiceImpl extends SocialService {

    override def doLike(dto: LikeDTO): ServiceResponse[BooleanDTO] = {
      val like = likeAssetDTOToModel(dto)
      socialRepo.like(like) map {
        result => if (result) DTO(BooleanDTO(result)) else ErrorDTO(ErrorCode.LikeError)
      }
    }

  }
}
