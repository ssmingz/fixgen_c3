class PlaceHold {
  public void setArgs(String s) {
    log("The args attribute is deprecated. " + "Please use nested arg elements.", MSG_WARN);
    getCommandLine().createArgument().setLine(s);
  }
}
