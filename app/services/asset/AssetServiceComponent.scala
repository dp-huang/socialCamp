package services.asset

import dtos.AssetDTO
import services.ServiceResponse

/**
  * Created by admin on 3/20/16.
  */
trait AssetServiceComponent {

  val assetService: AssetService

  trait AssetService {

    def getAssetById(id: String): ServiceResponse[AssetDTO]
  }
}
