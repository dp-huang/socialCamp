import com.wix.accord.Violation
import dtos._
import models.{Asset, Like, User}
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

  def userDTOToModel(userDTO: UserDTO): User = {
    User(id = userDTO.id, email = userDTO.email, firstName = userDTO.firstName, lastName = userDTO.lastName)
  }

  def assetModelToDTO(asset: Asset): AssetDTO = {
    AssetDTO(id = asset.id, name = asset.name)
  }

  def addAssetDTOToModel(addAssetDTO: AddAssetDTO): Asset = {
    Asset(id = IdGenerator.newId(Some(classOf[Asset])), name = addAssetDTO.name)
  }

  def likeAssetDTOToModel(likeDTO: LikeDTO): Like = {
    Like(userId = likeDTO.userId, assetId = likeDTO.assetId, created = System.currentTimeMillis())
  }
}
