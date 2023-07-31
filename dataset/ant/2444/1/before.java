class PlaceHold {
  public void testFixFileExclusive() throws Exception {
    expectBuildExceptionContaining(
        "testFixFileExclusive", ERROR_FILE_AND_SRCDIR, ERROR_FILE_AND_SRCDIR);
  }
}
