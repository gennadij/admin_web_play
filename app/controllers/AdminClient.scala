package controllers

import play.api.libs.json.JsValue
import play.api.libs.json.JsValue.jsValueToJsLookup

/**
  * Created by gennadi on 01.11.16.
  */
object AdminClient {


  def messageHandler(message: JsValue): JsValue = {
    (message\"id").toString match {
      case "001" => {

      }
      case _ => ""
    }

    null
  }


}
