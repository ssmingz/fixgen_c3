class PlaceHold {
  public void setExecutable(String e) {
    throw new BuildException(
        (taskType + " doesn\'t support the executable") + " attribute", getLocation());
  }
}
