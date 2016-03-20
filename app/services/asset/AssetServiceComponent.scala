package services.asset

import dtos.{AddAssetDTO, AssetDTO, StringDTO}
import services.ServiceResponse

/**
  * Created by admin on 3/20/16.
  */
trait AssetServiceComponent {

  val assetService: AssetService

  trait AssetService {

    def getAssetById(id: String): ServiceResponse[AssetDTO]

    def createAsset(dto: AddAssetDTO): ServiceResponse[StringDTO]
  }
}
