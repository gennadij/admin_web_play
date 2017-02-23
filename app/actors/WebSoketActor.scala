package actors

import play.api.mvc._
import play.api.libs.streams._
import akka.actor.ActorSystem
import akka.stream.Materializer
import javax.inject.Inject
import akka.actor._
import scala.Some
import play.api.libs.json.Json
import play.api.Logger

class CurrentConfig() {
//  val counter: List[Int] = List(1,2,3)
  
  var counter: Int = 0
}

object WebSocketActor {
  def props(out: ActorRef, request: RequestHeader) = Props(new WebSocketActor(out, request))
}
//http://doc.akka.io/docs/akka/current/scala/actors.html
//http://blog.scalac.io/2015/07/30/websockets-server-with-akka-http.html
//https://github.com/playframework/play-websocket-scala
//http://groz.github.io/scala/practical/chat/
//http://mandubian.com/2013/09/22/play-actor-room/
//{"dtoId":6,"dto":"ConfigTree","params":{"configId":"#41:13"}}

class WebSocketActor(out: ActorRef, request: RequestHeader) extends Actor{
  import play.api.libs.json.JsValue
  def receive: Receive = {
    case msg: JsValue => {
      Logger.debug("=SessionId: " + request.session.get("uuid"))
      (msg \ "dto").asOpt[String] match {
        case Some("StartConfig") => {
          Logger.debug(msg.toString())
          out ! msg
        }
        case Some("NextStep") => {
          Logger.debug(msg.toString())
          out ! msg
        }
        case _ => Logger.debug("Error")
      }
      
      
//      out ! handelMessage(msg)
    }
    case _ => Logger.debug("Error")
//      context.become(withUuid(uuid, 1), true)
//      val config = new CurrentConfig()
      
//      config.counter = config.counter + 1
      
//      Logger.debug("Counter " + config.counter)
      
//      val uuid = request.session.get("uuid")
//      
////      Logger.debug("UUID " + uuid.toString())
//      (request.session.get("uuid")) match {
//        case Some(uuid) => 
//          Logger.debug("receive " + msg.toString())
//          context.become(withUuid(uuid, 1), true)
//          out ! msg
//        case _ => 
//          Logger.debug("Error 1")
//      }
      
//      out ! handelMessage(msg)
//    }
//      Logger.debug("Receive " + msg.toString())
//      (msg \ "dto").asOpt[String] match {
//        case Some("test") => out ! Json.obj("test" -> "test")
//        case _ => out ! Json.obj("error" -> "keinen Treffer")
//      }
  }
  
  override def postStop() {
    Logger.debug("Websocket with uuid " + request.session.get("uuid") + " is disconnected!")
  }
  
  def withUuid(uuid: String, countup: Int): Receive = {
    case msg: JsValue => {
      Logger.debug("vor Case " + msg)
      ((msg \ "echo").asOpt[Boolean]) match {
        case Some(true) => 
          Logger.debug("Counter " + countup)
          Logger.debug("msg" + msg.toString())
          context.parent ! Json.obj(
      		  				"answareToMe" -> true,
      		  				"uuid" -> uuid,
      		  				"count" -> countup)
          context.become(withUuid(uuid,countup+1), true)
        case _ => 
          Logger.debug("case _ in withUuid " + msg.toString())
          Logger.debug("Error 2")
      }
      
    }
  }
}