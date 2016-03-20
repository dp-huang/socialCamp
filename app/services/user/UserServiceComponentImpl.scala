package services.user

import dtos._
import models.User
import repositories.UserRepoComponentImpl
import services._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by admin on 3/17/16.
  */
trait UserServiceComponentImpl extends UserServiceComponent {
  this: UserRepoComponentImpl =>

  val userService = new UserServiceImpl

  class UserServiceImpl extends UserService {

    override def getUserById(id: String): ServiceResponse[UserDTO] = {
      userRepo.getUser(id) map {
        case Some(a) => DTO(userModelToDTO(a))
        case _ => ErrorDTO(ErrorCode.UserNotExist)
      }
    }

    override def addUser(dto: AddUserDTO): ServiceResponse[String] = {
      val user = addUserDTOToModel(dto)
      userRepo.addUser(user) map {
        result =>
          DTO(if (result) user.id else "")
      }
    }

  }
}
