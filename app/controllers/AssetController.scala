package controllers

import javax.inject.{Inject, Singleton}

import dtos.{AddAssetDTO, DTOJsonFormat}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.asset.AssetServiceComponent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by admin on 3/20/16.
  */
@Singleton
class AssetController @Inject() (comp: AssetServiceComponent) extends Controller with DTOJsonFormat {

  def getAssetById(id: String) = Action.async {
    request =>
      comp.assetService.getAssetById(id).map(r => Ok(Json.toJson(r)))
  }

  def createAsset() = Action.async {
    request =>
      request.body.asJson map {
        json =>
          val dto = json.as[AddAssetDTO]
          comp.assetService.createAsset(dto).map(r => Ok(Json.toJson(r)))
      } getOrElse {
        Future.successful(BadRequest("Incorrect Json data"))
      }
  }
}
