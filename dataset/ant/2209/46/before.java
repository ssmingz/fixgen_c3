class PlaceHold {
  public void testNoFile() {
    expectBuildException("testNoFile", "file is required");
  }
}
