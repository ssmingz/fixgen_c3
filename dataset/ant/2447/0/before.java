class PlaceHold {
  public void log(Target target, String message, int msgLevel) {
    fireMessageLogged(target, message, msgLevel);
  }
}
