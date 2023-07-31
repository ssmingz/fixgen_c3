class PlaceHold {
  public void setJvmargs(String s) {
    log("The args attribute is deprecated. " + "Please use nested arg elements.", MSG_WARN);
    cmdl.createVmArgument().setLine(s);
  }
}
