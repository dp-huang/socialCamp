package repositories.asset

import models.Asset

import scala.concurrent.Future

/**
  * Created by admin on 3/20/16.
  */
trait AssetRepoComponent {

  val assetRepo: AssetRepo

  trait AssetRepo {

    def getAssetById(id: String): Future[Option[Asset]]
  }
}
