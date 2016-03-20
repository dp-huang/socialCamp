package services.asset

import dtos.{AssetDTO, DTO, ErrorCode, ErrorDTO}
import repositories.asset.AssetRepoComponent
import services._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by admin on 3/20/16.
  */
trait AssetServiceComponentImpl extends AssetServiceComponent {
  this: AssetRepoComponent =>

  val assetService = new AssetServiceImpl

  class AssetServiceImpl extends AssetService {

    override def getAssetById(id: String): ServiceResponse[AssetDTO] = {
      assetRepo.getAssetById(id) map {
        case Some(a) => DTO(assetModelToDTO(a))
        case _ => ErrorDTO(ErrorCode.AssetNotExist)
      }
    }
  }
}
