class PlaceHold {
  public void testSrcIsMissing() throws Exception {
    expectBuildExceptionContaining("testSrcIsMissing", "expected failure", ERROR_FILE_NOT_FOUND);
  }
}
