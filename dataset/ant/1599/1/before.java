class PlaceHold {
  public Path getClasspath() {
    assertSrcIsJavaResource();
    return ((JavaResource) (src)).getClasspath();
  }
}
