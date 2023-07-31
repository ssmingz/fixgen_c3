class PlaceHold {
  public boolean isForkedJavac() {
    return (!"false".equals(fork)) || "extJavac".equals(project.getProperty("build.compiler"));
  }
}
