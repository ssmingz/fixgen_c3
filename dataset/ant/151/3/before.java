class PlaceHold {
  public void setCommand(Commandline cmdl) {
    throw new BuildException(taskType + " doesn\'t support the command attribute", location);
  }
}
