package controllers

import javax.inject._
import play.api._
import play.api.libs.iteratee.{Concurrent, Enumerator, Iteratee}
import play.api.mvc._
import play.api.libs.ws._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.libs.json.JsValue.jsValueToJsLookup
import play.api.libs.json.Json.toJsFieldJsValueWrapper
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeControllerTest @Inject()(ws: WSClient) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }


  def wsEcho = WebSocket.using[JsValue] {
    Ok(views.html.index("Your new application is ready."))
    
    request => {
      Logger.info(s"wsEcho, client connected.")
      var channel: Option[Concurrent.Channel[JsValue]] = None

      val outEnumerator: Enumerator[JsValue] = Concurrent.unicast(c => channel = Some(c))

      val inIteratee: Iteratee[JsValue, Unit] = Iteratee.foreach[JsValue](receivedString => {
        Logger.info(s"wsEcho, received: $receivedString")


        val jsonReceivedString = receivedString.asInstanceOf[JsValue]
        Logger.info((receivedString\"method").toString)
        channel.foreach(_.push(Json.obj("test" -> "test2")))
      })
      (inIteratee, outEnumerator)
    }
  }
}
