class PlaceHold {
  public Path createClasspath() {
    assertSrcIsJavaResource();
    return ((JavaResource) (src)).createClasspath();
  }
}
