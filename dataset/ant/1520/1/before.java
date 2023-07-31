class PlaceHold {
  public void tearDown() {
    if (supportsSymlinks) {
      executeTarget("teardown");
    }
  }
}
