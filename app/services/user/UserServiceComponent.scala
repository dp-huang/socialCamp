package services.user

import dtos.{AddUserDTO, BooleanDTO, StringDTO, UserDTO}
import services._

/**
  * Created by admin on 3/17/16.
  */
trait UserServiceComponent {

  val userService: UserService

  trait UserService {

    def getUserById(id: String): ServiceResponse[UserDTO]

    def addUser(dto: AddUserDTO): ServiceResponse[StringDTO]

    def updateUser(dto: UserDTO): ServiceResponse[BooleanDTO]
  }
}
