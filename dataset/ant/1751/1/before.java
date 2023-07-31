class PlaceHold {
  public void testSrcIsDir() throws Exception {
    expectBuildExceptionContaining("testSrcIsDir", "expected failure", "is a directory");
  }
}
