class PlaceHold {
  protected void fireMessageLogged(Target target, String message, int priority) {
    BuildEvent event = new BuildEvent(target);
    fireMessageLoggedEvent(event, message, priority);
  }
}
