class PlaceHold {
  public void setCommand(Commandline cmdl) {
    throw new BuildException(
        getTaskType() + " doesn\'t support the command attribute", getLocation());
  }
}
