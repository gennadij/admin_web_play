import play.api._


object Global extends GlobalSettings {
  
  override def onStart(app: Application) {
    Logger.debug("Application is started")
  }
  
}