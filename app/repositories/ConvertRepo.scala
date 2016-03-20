package repositories

import com.couchbase.client.java.document.RawJsonDocument
import play.api.libs.json.{Json, Reads}
import rx.Observable
import rx.lang.scala.JavaConversions

import scala.collection.JavaConversions._
import scala.concurrent.{Future, Promise}

/**
  * Created by admin on 3/19/16.
  */
trait ConvertRepo {

  def convertGet[T](observable: Observable[java.util.List[String]])(implicit ev: Reads[T]): Future[List[T]] = {
    val promise = Promise[List[T]]
    JavaConversions.toScalaObservable(observable).map(a => a.map(Json.parse(_).as[T]))
        .subscribe(x => promise.success(x.toList), e => promise.failure(e), () => ())
    promise.future
  }

  def convertSet[T](observable: Observable[RawJsonDocument])(implicit ev: Reads[T]): Future[T] = {
    val promise = Promise[T]
    JavaConversions.toScalaObservable(observable).map(a => Json.parse(a.content()).as[T])
        .subscribe(x => promise.success(x), e => promise.failure(e), () => ())
    promise.future
  }
}
