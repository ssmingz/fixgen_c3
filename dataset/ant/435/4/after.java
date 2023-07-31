class PlaceHold {
  public void setCommand(String e) {
    throw new BuildException(
        getTaskType() + " doesn\'t support the command attribute", getLocation());
  }
}
