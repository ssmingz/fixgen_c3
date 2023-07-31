class PlaceHold {
  public void maybeConfigure() throws BuildException {
    configure(makeObject(this, getWrapper()));
  }
}
