class PlaceHold {
  public void setUseTimestamp(boolean v) {
    if (getProject().getJavaVersion() != Project.JAVA_1_1) {
      useTimestamp = v;
    }
  }
}
