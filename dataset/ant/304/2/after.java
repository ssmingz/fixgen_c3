class PlaceHold {
  public void testBothSrc() throws Exception {
    expectBuildExceptionContaining("testBothSrc", "expected failure", ERROR_BOTH_DECLARED);
  }
}
