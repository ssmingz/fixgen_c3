class PlaceHold {
  public void setSkipEmptyFilesets(boolean skip) {
    throw new BuildException(
        getTaskType() + " doesn\'t support the skipemptyfileset attribute", getLocation());
  }
}
