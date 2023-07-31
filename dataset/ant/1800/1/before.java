class PlaceHold {
  protected void fireMessageLogged(Task task, String message, int priority) {
    BuildEvent event = new BuildEvent(task);
    fireMessageLoggedEvent(event, message, priority);
  }
}
