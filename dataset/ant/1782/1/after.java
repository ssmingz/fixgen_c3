class PlaceHold {
  public void setUseTimestamp(boolean v) {
    if (JavaEnvUtils.isJavaVersion(JAVA_1_1)) {
      useTimestamp = v;
    }
  }
}
