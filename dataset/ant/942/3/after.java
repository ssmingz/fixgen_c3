class PlaceHold {
  public void maybeConfigure() throws BuildException {
    if (realThing != null) {
      return;
    }
    configure(makeObject(this, getWrapper()));
  }
}
