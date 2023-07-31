class PlaceHold {
  public void setClasspath(Path classpath) {
    assertSrcIsJavaResource();
    ((JavaResource) (src)).setClasspath(classpath);
  }
}
