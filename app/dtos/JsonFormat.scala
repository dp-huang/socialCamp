package dtos

import play.api.libs.json.Json

/**
  * Created by admin on 3/18/16.
  */
trait JsonFormat {

  implicit val userResponseFormat = Json.format[UserDTO]

}
