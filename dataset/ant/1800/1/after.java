class PlaceHold {
  protected void fireMessageLogged(Task task, String message, int priority) {
    fireMessageLogged(task, message, null, priority);
  }
}
