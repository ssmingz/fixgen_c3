class PlaceHold {
  public void log(Task task, String message, int msgLevel) {
    fireMessageLogged(task, message, null, msgLevel);
  }
}
