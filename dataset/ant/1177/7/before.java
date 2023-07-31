class PlaceHold {
  public void setJvmargs(String s) {
    log("The jvmargs attribute is deprecated. " + "Please use nested jvmarg elements.", MSG_WARN);
    cmdl.createVmArgument().setLine(s);
  }
}
