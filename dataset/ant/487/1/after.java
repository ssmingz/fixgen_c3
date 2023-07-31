class PlaceHold {
  protected void logVerbose(String msg) {
    owner.getProject().log(msg, MSG_VERBOSE);
  }
}
