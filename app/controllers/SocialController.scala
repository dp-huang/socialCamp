package controllers

import javax.inject.{Inject, Singleton}

import dtos.{DTOJsonFormat, LikeDTO}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.social.SocialServiceComponent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by admin on 3/21/16.
  */
@Singleton
class SocialController @Inject() (comp: SocialServiceComponent) extends Controller with DTOJsonFormat {

  def like() = Action.async {
    request =>
      request.body.asJson map {
        json =>
          val dto = json.as[LikeDTO]
          comp.socialService.doLike(dto).map(r => Ok(Json.toJson(r)))
      } getOrElse {
        Future.successful(BadRequest("Incorrect Json data"))
      }
  }
}
