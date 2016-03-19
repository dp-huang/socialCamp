package controllers

import javax.inject.{Inject, Singleton}

import dtos.{AddUserDTO, DTOJsonFormat}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.user.UserServiceComponent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by admin on 3/17/16.
  */
@Singleton
class UserController @Inject() (comp: UserServiceComponent) extends Controller with DTOJsonFormat {

  def getUserById(id: String) = Action.async {
    request =>
      comp.userService.getUserById(id).map(r => Ok(Json.toJson(r)))
  }

  def createUser() = Action.async {
    request =>
      request.body.asJson map {
        json =>
          val dto = json.as[AddUserDTO]
          comp.userService.addUser(dto).map(r => Ok(Json.toJson(r)))
      } getOrElse {
        Future(BadRequest("Incorrect Json data"))
      }
  }
}
