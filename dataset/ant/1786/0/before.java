class PlaceHold {
  public boolean isForkedJavac() {
    return fork || "extJavac".equals(project.getProperty("build.compiler"));
  }
}
