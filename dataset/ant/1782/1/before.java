class PlaceHold {
  public void setUseTimestamp(boolean v) {
    if (Project.getJavaVersion() != Project.JAVA_1_1) {
      useTimestamp = v;
    }
  }
}
