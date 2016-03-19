package models

import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import play.api.libs.json.Json

/**
  * Created by admin on 3/19/16.
  */
trait ModelJsonFormat {

  def jsonToObject(json: JsonDocument): User = {
    val content = json.content()
    User(
      id = json.id(),
      email = content.getString("email"),
      firstName = if (content.containsKey("firstName")) Some(content.getString("firstName")) else None,
      lastName = if (content.containsKey("lastName")) Some(content.getString("firstName")) else None
    )
  }

  def objectToJson(user: User): JsonObject = {
    val jsonObj = JsonObject.empty().put("email", user.email)

    if (user.firstName.isDefined) {
      jsonObj.put("firstName", user.firstName.get)
    }

    if (user.lastName.isDefined) {
      jsonObj.put("lastName", user.lastName.get)
    }

    jsonObj
  }

}
