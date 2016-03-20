package services.user

import dtos._
import repositories.user.UserRepoComponentImpl
import services._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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
      userRepo.setUser(user) map {
        result =>
          if (result) DTO(user.id) else ErrorDTO(ErrorCode.UserCreatedFailed)
      }
    }

    override def updateUser(dto: UserDTO): ServiceResponse[Boolean] = {
      userRepo.getUser(dto.id) flatMap {
        user =>
          user match {
            case Some(u) =>
              val updateUser = userDTOToModel(dto)
              userRepo.setUser(updateUser) map {
                result =>
                  if (result) DTO(result) else ErrorDTO(ErrorCode.UserUpdatedFailed)
              }
            case _ =>
              Future.successful(ErrorDTO(ErrorCode.UserNotExist))
          }
      }
    }

  }
}
