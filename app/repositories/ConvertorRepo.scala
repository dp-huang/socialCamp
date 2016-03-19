package repositories

import com.couchbase.client.java.document.JsonDocument
import models.{ModelJsonFormat, User}
import rx.Observable
import rx.lang.scala.JavaConversions

import scala.collection.JavaConversions._
import scala.concurrent.{Future, Promise}

/**
  * Created by admin on 3/19/16.
  */
trait ConvertorRepo extends ModelJsonFormat {

  def convertObservablesToFuture(observables: Observable[java.util.List[JsonDocument]]): Future[List[User]] = {

    val promise = Promise[List[User]]
    JavaConversions.toScalaObservable(observables).map(a => a.map(b => jsonToObject(b)))
      .subscribe(x => promise.success(x.toList), e => promise.failure(e), () => ())
    promise.future

  }

  def convertObservableToFuture(observable: Observable[JsonDocument]): Future[User] = {
    val promise = Promise[User]
    JavaConversions.toScalaObservable(observable).map(a => jsonToObject(a))
        .subscribe(x => promise.success(x), e => promise.failure(e), () => ())
    promise.future
  }
}
