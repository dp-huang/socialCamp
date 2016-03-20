package models

import play.api.libs.json.Json

/**
  * Created by admin on 3/19/16.
  */
trait ModelJsonFormat {

  implicit val userFormat = Json.format[User]

}
