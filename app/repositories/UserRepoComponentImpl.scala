package repositories

import com.couchbase.client.java.document.JsonDocument
import models.{ModelJsonFormat, User}
import repositories.bridge.CouchbaseDriver
import rx.lang.scala.JavaConversions

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by admin on 3/18/16.
  */
trait UserRepoComponentImpl extends UserRepoComponent with ConvertorRepo with BaseRepo {

  val userRepo = new UserRepoCouch

  class UserRepoCouch extends UserRepo {

    override def getUser(id: String): Future[Option[User]] = {
      convertObservablesToFuture(couchbaseDriver.asyncGetById(id)).map(_.headOption)
    }

    override def addUser(user: User): Future[Boolean] = {
      val json = JsonDocument.create(user.id, objectToJson(user))
      convertObservableToFuture(couchbaseDriver.asyncSet(json)).map(a => true)
    }
  }

}
