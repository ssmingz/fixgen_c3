class PlaceHold {
  public void setCommand(String e) {
    throw new BuildException(taskType + " doesn\'t support the command attribute", getLocation());
  }
}
