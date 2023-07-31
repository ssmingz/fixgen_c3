class PlaceHold {
  public void testNoSrc() throws Exception {
    expectBuildExceptionContaining("testNoSrc", "expected failure", ERROR_NONE_DECLARED);
  }
}
