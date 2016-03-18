package services.user

import dtos.UserDTO
import models.User

import scala.concurrent.Future

/**
  * Created by admin on 3/17/16.
  */
trait UserServiceComponent {

  val userService: UserService

  trait UserService {

    def getUserById(id: String): Future[UserDTO]
  }
}