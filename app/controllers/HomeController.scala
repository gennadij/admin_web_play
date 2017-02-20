package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import akka.actor.ActorRef
import play.api.libs.streams.ActorFlow
import actors.WebSocketActor
import akka.stream.Materializer
import akka.actor.ActorSystem
import play.api.libs.json.JsValue

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (implicit system: ActorSystem, materializer: Materializer) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action { implicit request => 
//    val uuid = java.util.UUID.randomUUID.toString
    Ok(views.html.index("Choose one"))
  }

  def indexStatefull = Action { implicit request => 
    val uuid = java.util.UUID.randomUUID.toString
    Logger.debug("UUID " + uuid)
    Ok(views.html.indexStatefull("Welcome!")).withSession(
        ("uuid" -> uuid))
  }
  
  
  def socket = WebSocket.accept[JsValue, JsValue] { request =>
    Logger.debug("Session " + request.session.get("uuid").toString())
    
    ActorFlow.actorRef(out => WebSocketActor.props(out, request))
  }
}
