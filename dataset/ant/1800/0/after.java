class PlaceHold {
  protected void fireMessageLogged(Target target, String message, int priority) {
    fireMessageLogged(target, message, null, priority);
  }
}
