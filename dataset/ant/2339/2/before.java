class PlaceHold {
  public void setClasspathRef(Reference r) {
    assertSrcIsJavaResource();
    ((JavaResource) (src)).setClasspathRef(r);
  }
}
