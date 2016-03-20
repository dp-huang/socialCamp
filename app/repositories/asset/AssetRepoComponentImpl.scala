package repositories.asset

import com.couchbase.client.java.document.RawJsonDocument
import models.{Asset, ModelJsonFormat}
import play.api.libs.json.Json
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

    override def createAsset(asset: Asset): Future[Boolean] = {
      val rawJsonDocument = RawJsonDocument.create(asset.id, Json.toJson(asset).toString())
      convertSet[Asset](couchbaseDriver.asyncSet(rawJsonDocument)).map(a => true)
    }
  }
}
