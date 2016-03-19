package dtos

/**
  * Created by admin on 3/18/16.
  */
case class UserDTO(id: String, email: String, firstName: Option[String], lastName: Option[String])

case class AddUserDTO(email: String, firstName: Option[String], lastName: Option[String])
