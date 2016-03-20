import com.wix.accord.Violation
import dtos.{AddUserDTO, ResponseDTO, UserDTO}
import models.User
import utils.IdGenerator

import scala.concurrent.Future

/**
  * Created by admin on 3/18/16.
  */
package object services {

  type ServiceResponse[T] = Future[ResponseDTO[T]]

  implicit def niceMessage(errs: Set[Violation]): String = {
    s"${errs.map(er => s"${er.description.getOrElse("field")} ${er.constraint}").mkString(",")}"
  }

  def userModelToDTO(user: User): UserDTO = {
    UserDTO(id = user.id, email = user.email, firstName = user.firstName, lastName = user.lastName)
  }

  def addUserDTOToModel(addUserDTO: AddUserDTO): User = {
    User(id = IdGenerator.newId(Some(classOf[User])), email = addUserDTO.email, firstName = addUserDTO.firstName, lastName = addUserDTO.lastName)
  }
}
