package utils

import models.{Asset, User}

import scala.util.Random

/**
  * Created by admin on 3/19/16.
  */
object IdGenerator {

  val random = new Random()

  val digits = ("0123456789abcdefghijklmnopqrstuvwxyz").toCharArray

  def newId(classType: Option[Class[_]] = None): String = {
    val sb = new StringBuilder
    val prefix = classType match {
      case Some(a) if a == User.getClass || a == classOf[User] =>
        "M"
      case Some(a) if a == Asset.getClass || a == classOf[Asset] =>
        "A"
      case _ => ""
    }
    sb.append(prefix)
    for (i <- 1 to 6) {
      sb.append(digits(random.nextInt(digits.length)))
    }
    sb.append(java.lang.Long.toString(System.nanoTime(), Character.MAX_RADIX))
    sb.toString.toUpperCase
  }
}
