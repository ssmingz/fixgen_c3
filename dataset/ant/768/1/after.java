class PlaceHold {
  public void targetStarted(BuildEvent event) {
    if (initialized) {
      Log log = getLog("org.apache.tools.ant.Target", event.getTarget().getName());
      realLog(log, "Start: " + event.getTarget().getName(), MSG_VERBOSE, null);
    }
  }
}
