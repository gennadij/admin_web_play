//package actors
//
//import akka.actor.ActorRef
//import akka.actor.Props
//
//object WebsocketActor {
//  def props(out: ActorRef) = Props(new WebsocketActor(out))
//}
//
//
//class WebsocketActor(out: ActorRef) {
//  
//  out ! "Hello"
//  
//  override def receive = {
//    case msg: String => 
//      println(msg)
//      out ! msg
//  }
//  
//  
//  override def postStop() {
//    println("Disconnected.")
//  }
//  
//  def actorSocket = WebSoket
//}