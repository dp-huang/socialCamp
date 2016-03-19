package models

/**
  * Created by admin on 3/17/16.
  */

case class User(id: String, email: String, firstName: Option[String] = None, lastName: Option[String] = None)
