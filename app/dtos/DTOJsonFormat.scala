package dtos

import play.api.libs.json.Json

/**
  * Created by admin on 3/18/16.
  */
trait DTOJsonFormat {

  implicit val userDTOFormat = Json.format[UserDTO]
  implicit val addUserDTOFormat = Json.format[AddUserDTO]
}
