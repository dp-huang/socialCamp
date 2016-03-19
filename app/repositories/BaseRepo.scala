package repositories

import org.reactivecouchbase.play.PlayCouchbase
import play.api.Play
import play.api.Play.current

/**
  * Created by admin on 3/19/16.
  */
trait BaseRepo {

  lazy val userBucketName = Play.current.configuration.getString("userBucket").getOrElse("user")

  lazy val userBucket = PlayCouchbase.bucket(userBucketName)
}
