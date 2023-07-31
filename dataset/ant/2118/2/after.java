class PlaceHold {
  public void setArgs(String s) {
    log("The args attribute is deprecated. " + "Please use nested arg elements.", MSG_WARN);
    cmdl.createArgument().setLine(s);
  }
}
