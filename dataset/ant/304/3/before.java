class PlaceHold {
  public void testSrcIsMissing() throws Exception {
    expectBuildExceptionContaining("testSrcIsMissing", "expected failure", "does not exist");
  }
}
