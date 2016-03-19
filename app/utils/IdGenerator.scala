package utils

import scala.util.Random

/**
  * Created by admin on 3/19/16.
  */
object IdGenerator {

  val random = new Random()

  val digits = ("0123456789abcdefghijklmnopqrstuvwxyz").toCharArray

  def newId(): String = {
    val sb = new StringBuilder
    for (i <- 1 to 6) {
      sb.append(digits(random.nextInt(digits.length)))
    }
    sb.append(java.lang.Long.toString(System.nanoTime(), Character.MAX_RADIX))
    sb.toString
  }
}
