package services.user

import dtos.{AddUserDTO, UserDTO}
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

    override def getUserById(id: String): Future[UserDTO] = {
      val testUser = userModelToDTO(User("test_id", "email@email.com"))
      Future.successful(testUser)
    }

    override def addUser(dto: AddUserDTO): Future[String] = {
      val user = addUserDTOToModel(dto)
      userRepo.addUser(user) map {
        result =>
          if (result) user.id else ""
      }
    }

  }
}
