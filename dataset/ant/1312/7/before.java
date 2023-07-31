class PlaceHold {
  public void log(Task task, String message, int msgLevel) {
    fireMessageLogged(task, message, msgLevel);
  }
}
