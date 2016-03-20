package dtos

import play.api.libs.json.{JsNumber, JsObject, Json, Writes}

/**
  * Created by admin on 3/18/16.
  */
trait DTOJsonFormat {

  implicit def genericFormat[T](implicit fmt: Writes[T]): Writes[ResponseDTO[T]] =
    new Writes[ResponseDTO[T]] {
      def writes(o: ResponseDTO[T]) = o match {
        case DTO(value) => fmt.writes(value).as[JsObject] ++ Json.obj("er" -> ErrorCode.NoError)
        //case DTO(value) => Json.toJson[T](value)
        case ErrorDTO(code, message) => Json.obj("er" -> JsNumber(code), "erMessage" -> message)
      }
    }
  
  implicit val userDTOFormat = Json.format[UserDTO]
  implicit val addUserDTOFormat = Json.format[AddUserDTO]
}
