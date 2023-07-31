class PlaceHold {
  protected void logError(String msg) {
    _owner.getProject().log(msg, MSG_ERR);
  }
}
