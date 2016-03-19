package repositories

import play.api.Play
import repositories.bridge.CouchbaseDriver

import scala.collection.JavaConverters._

/**
  * Created by admin on 3/19/16.
  */
trait BaseRepo {

  lazy private val userServers = Play.current.configuration.getString("couchbase.hosts").getOrElse("localhost").split(",").toList.asJava
  lazy private val userBucketName = Play.current.configuration.getString("couchbase.bucket").getOrElse("user")
  lazy private val userName = Play.current.configuration.getString("couchbase.user")
  lazy private val userPassword = Play.current.configuration.getString("couchbase.pass")


  lazy val couchbaseDriver = new CouchbaseDriver(userServers, userBucketName)
}
