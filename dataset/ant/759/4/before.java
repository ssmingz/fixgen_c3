class PlaceHold {
  public void testNoFile() throws Exception {
    expectBuildExceptionContaining("testNoFile", "no file at file attribute", ERROR_NO_FILE);
  }
}
