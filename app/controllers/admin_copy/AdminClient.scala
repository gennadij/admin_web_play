package controllers

import akka.io.Tcp.Message
import play.api.libs.json.JsValue
import play.mvc.BodyParser.Json

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
