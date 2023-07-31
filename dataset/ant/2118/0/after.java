class PlaceHold {
  public void setCommand(Commandline cmdl) {
    log(
        "The command attribute is deprecated. "
            + "Please use the executable attribute and nested arg elements.",
        MSG_WARN);
    this.cmdl = cmdl;
  }
}
