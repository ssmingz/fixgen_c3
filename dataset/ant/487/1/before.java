class PlaceHold {
  protected void logVerbose(String msg) {
    _owner.getProject().log(msg, MSG_VERBOSE);
  }
}
