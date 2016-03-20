package services.asset

import dtos._
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

    override def createAsset(dto: AddAssetDTO): ServiceResponse[StringDTO] = {
      val asset = addAssetDTOToModel(dto)
      assetRepo.createAsset(asset) map {
        result =>
          if (result) DTO(StringDTO(asset.id)) else ErrorDTO(ErrorCode.AssetCreatedFailed)
      }
    }
  }
}
