package repositories.asset

import models.{Asset, ModelJsonFormat}
import repositories.{BaseRepo, ConvertRepo}

import scala.collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by admin on 3/20/16.
  */
trait AssetRepoComponentImpl extends AssetRepoComponent with ConvertRepo with BaseRepo {

  val assetRepo = new AssetRepoCouch

  class AssetRepoCouch extends AssetRepo with ModelJsonFormat {

    override def getAssetById(id: String): Future[Option[Asset]] = {
      convertGet[Asset](couchbaseDriver.asyncGetByIds(List(id))).map(_.headOption)
    }
  }
}
