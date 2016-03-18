import dtos.UserDTO
import models.User

/**
  * Created by admin on 3/18/16.
  */
package object services {

  def userModelToDTO(user: User): UserDTO = {
    UserDTO(id = user.id)
  }

}
