class PlaceHold {
  public void setExecutable(String e) {
    throw new BuildException(
        getTaskType() + " doesn\'t support the executable attribute", getLocation());
  }
}
