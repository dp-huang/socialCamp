package repositories

import play.api.Play

import scala.collection.JavaConverters._

/**
  * Created by admin on 3/19/16.
  */
trait BaseRepo {

  lazy val userServers = Play.current.configuration.getString("couchbase.hosts").getOrElse("localhost").split(",").toList.asJava
  lazy val userBucketName = Play.current.configuration.getString("couchbase.bucket").getOrElse("user")
  lazy val userName = Play.current.configuration.getString("couchbase.user")
  lazy val userPassword = Play.current.configuration.getString("couchbase.pass")
}
