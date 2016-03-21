package services.social

import dtos.{BooleanDTO, LikeDTO}
import services.ServiceResponse

/**
  * Created by admin on 3/21/16.
  */
trait SocialServiceComponent {

  val socialService: SocialService

  trait SocialService {

    def doLike(dto: LikeDTO): ServiceResponse[BooleanDTO]
  }

}
