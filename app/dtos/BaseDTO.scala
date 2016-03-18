package dtos

/**
  * Created by admin on 3/18/16.
  */

/**
  * Error code enumeration
  */
object ErrorCode extends Enumeration {
  val NoError = -1
  val UnKnown = 0
}


/**
  * Base class for response
  *
  * @tparam T the type of the dto object
  */
sealed abstract class ResponseDTO[T] {
  def isError: Boolean
}

/**
  * container for the object dto
  *
  * @param d the dto object
  * @tparam T the type of the dto object
  */
case class DTO[T](d: T) extends ResponseDTO[T] {
  def isError = false

  /**
    * helper for getting the inner value
    *
    * @return the object of type T contained by this wrapper
    */
  def get() = d
}

/**
  * The generic DTO representing the error information
  *
  * @param errCode the error code
  * @param errMessage the detailed error message
  * @tparam T the type of the dto object
  */
case class ErrorDTO[T](errCode: Int = ErrorCode.UnKnown, errMessage: String = "") extends ResponseDTO[T] {
  def isError = true
}