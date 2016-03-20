import dtos.{AddUserDTO, UserDTO}
import models.User
import utils.IdGenerator

/**
  * Created by admin on 3/18/16.
  */
package object services {

  def userModelToDTO(user: User): UserDTO = {
    UserDTO(id = user.id, email = user.email, firstName = user.firstName, lastName = user.lastName)
  }

  def addUserDTOToModel(addUserDTO: AddUserDTO): User = {
    User(id = IdGenerator.newId(Some(classOf[User])), email = addUserDTO.email, firstName = addUserDTO.firstName, lastName = addUserDTO.lastName)
  }
}
