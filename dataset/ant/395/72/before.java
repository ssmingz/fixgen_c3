class PlaceHold {
  public void setUseTimestamp(boolean v) {
    if (project.getJavaVersion() != Project.JAVA_1_1) {
      useTimestamp = v;
    }
  }
}
