package repositories

import models.{ModelJsonFormat, User}
import repositories.bridge.CouchbaseBridge
import rx.lang.scala.JavaConversions

import scala.concurrent.{Future, Promise}

/**
  * Created by admin on 3/18/16.
  */
trait UserRepoComponentImpl extends UserRepoComponent with BaseRepo {

  val userRepo = new UserRepoCouch

  class UserRepoCouch extends UserRepo with ModelJsonFormat {

    override def getUser(id: String): Future[Option[User]] = {
      val couchbaseBridge = new CouchbaseBridge(userServers, userBucketName)
      val promise = Promise[Option[User]]()
      JavaConversions.toScalaObservable(couchbaseBridge.get(id)).toList.map(a => if (a.isEmpty) None else Some(User(id = a.head.content().toString, email = "email")))
        .subscribe(x => promise.success(x), e => promise.failure(e), () => ())
      promise.future
    }

    override def addUser(user: User): Future[Boolean] = {
      Future.successful(true)
    }
  }

}
