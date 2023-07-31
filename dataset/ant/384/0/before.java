class PlaceHold {
  public void testNoFile() {
    expectBuildExceptionContaining("testNoFile", "missing file", "file");
  }
}
