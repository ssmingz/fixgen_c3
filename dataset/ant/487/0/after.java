class PlaceHold {
  protected void logError(String msg) {
    owner.getProject().log(msg, MSG_ERR);
  }
}
