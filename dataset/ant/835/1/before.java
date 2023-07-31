class PlaceHold {
  public void log(String message, int msgLevel) {
    fireMessageLogged(this, message, msgLevel);
  }
}
