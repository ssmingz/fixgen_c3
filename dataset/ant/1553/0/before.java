class PlaceHold {
  protected void checkConfiguration() {
    if ("execon".equals(getTaskName())) {
      log("!! execon is deprecated. Use apply instead. !!");
    }
    super.checkConfiguration();
    if ((filesets.size() == 0) && (resources.size() == 0)) {
      throw new BuildException("no resources specified", getLocation());
    }
    if ((targetFilePos != null) && (mapperElement == null)) {
      throw new BuildException("targetfile specified without mapper", getLocation());
    }
    if ((destDir != null) && (mapperElement == null)) {
      throw new BuildException("dest specified without mapper", getLocation());
    }
    if (mapperElement != null) {
      mapper = mapperElement.getImplementation();
    }
  }
}
