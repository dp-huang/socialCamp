package repositories.bridge

import play.api.Play

/**
  * Created by admin on 3/19/16.
  */
object CouchbaseUtil {

  val userServers = Play.current.configuration.getString("couchbase.hosts").getOrElse("localhost")
  val userBucketName = Play.current.configuration.getString("couchbase.bucket").getOrElse("user")
  val userName = Play.current.configuration.getString("couchbase.user")
  val userPassword = Play.current.configuration.getString("couchbase.pass")

  def test() = {

  }

}
