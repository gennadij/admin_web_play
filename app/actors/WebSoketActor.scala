package actors

import play.api.mvc._
import play.api.libs.streams._
import akka.actor.ActorSystem
import akka.stream.Materializer
import javax.inject.Inject
import akka.actor._
import play.Logger
import scala.Some
import play.api.libs.json.Json
import play.api.libs.Jsonp

object WebSocketActor {
  def props(out: ActorRef) = Props(new WebSocketActor(out))
}

class WebSocketActor(out: ActorRef) extends Actor {
  import play.api.libs.json.JsValue
  def receive = {
    case msg: JsValue =>
      Logger.debug("Receive " + msg.toString())
      (msg \ "dto").asOpt[String] match {
        case Some("test") => out ! Json.obj("test" -> "test")
        case _ => out ! Json.obj("error" -> "keinen Treffer")
      }
  }
  
  override def postStop() {
    Logger.debug("Websocket is disconnected!")
  }
  
  
}