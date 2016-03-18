package services.user

import dtos.UserDTO
import models.User
import services._

import scala.concurrent.Future

/**
  * Created by admin on 3/17/16.
  */
trait UserServiceComponentImpl extends UserServiceComponent {

  val userService = new UserServiceImpl

  class UserServiceImpl extends UserService {

    override def getUserById(id: String): Future[UserDTO] = {
      val testUser = userModelToDTO(User("test_id"))
      Future.successful(testUser)
    }

  }
}
