package repositories

import com.couchbase.client.java.document.{JsonDocument, RawJsonDocument}
import models.{ModelJsonFormat, User}
import play.api.libs.json.Json
import repositories.bridge.CouchbaseDriver
import rx.lang.scala.JavaConversions

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.JavaConversions._

/**
  * Created by admin on 3/18/16.
  */
trait UserRepoComponentImpl extends UserRepoComponent with ConvertRepo with BaseRepo {

  val userRepo = new UserRepoCouch

  class UserRepoCouch extends UserRepo with ModelJsonFormat {

    override def getUser(id: String): Future[Option[User]] = {
      convertGet[User](couchbaseDriver.asyncGetByIds(List(id))).map(_.headOption)
    }

    override def addUser(user: User): Future[Boolean] = {
      val rawJsonDocument = RawJsonDocument.create(user.id, Json.toJson(user).toString())
      convertSet[User](couchbaseDriver.asyncSet(rawJsonDocument)).map(a => true)
    }
  }

}
