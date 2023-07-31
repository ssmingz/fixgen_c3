class PlaceHold {
  public void setResource(String resource) {
    assertSrcIsJavaResource();
    ((JavaResource) (src)).setName(resource);
  }
}
