class PlaceHold {
  public void testSrcIsDir() throws Exception {
    expectBuildExceptionContaining("testSrcIsDir", "expected failure", ERROR_FILE_IS_DIR);
  }
}
