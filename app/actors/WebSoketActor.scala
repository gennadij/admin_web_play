package actors

import play.api.mvc._
import play.api.libs.streams._
import akka.actor.ActorSystem
import akka.stream.Materializer
import javax.inject.Inject
import akka.actor._
import scala.Some
import play.api.libs.json.Json
import org.admin.AdminWeb
import play.api.Logger

class CurrentConfig() {
//  val counter: List[Int] = List(1,2,3)
  
  var counter: Int = 0
}

object WebSocketActor {
  def props(out: ActorRef, request: RequestHeader) = Props(new WebSocketActor(out, request))
}
//{"dtoId":6,"dto":"ConfigTree","params":{"configId":"#41:13"}}
class WebSocketActor(out: ActorRef, request: RequestHeader) extends Actor with AdminWeb{
  import play.api.libs.json.JsValue
  def receive: Receive = {
    case msg: JsValue => {
      
      context.become(withUuid(uuid, 1), true)
      val config = new CurrentConfig()
      
      config.counter = config.counter + 1
      
      Logger.debug("Counter " + config.counter)
      context.parent 
      out ! handelMessage(msg)
    }
//      Logger.debug("Receive " + msg.toString())
//      (msg \ "dto").asOpt[String] match {
//        case Some("test") => out ! Json.obj("test" -> "test")
//        case _ => out ! Json.obj("error" -> "keinen Treffer")
//      }
  }
  
  override def postStop() {
    Logger.debug("Websocket is disconnected!")
  }
  
  def withUuid(uuid: String, countup: Int): Receive = {
    Logger.debug("Counter " + countup)
    context.parent ! Json.obj(
      		  				"answareToMe" -> true,
      		  				"uuid" -> uuid,
      		  				"count" -> countup)
    context.become(withUuid(uuid,countup+1), true)
  }
  
}