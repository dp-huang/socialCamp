package dtos

/**
  * Created by admin on 3/18/16.
  */
case class UserDTO(id: String)

case class AddUserDTO(email: String, firstName: Option[String], lastName: Option[String])
