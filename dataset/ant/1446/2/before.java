class PlaceHold {
  public void testBoth() throws Exception {
    expectBuildExceptionContaining("testBoth", "error on two targets", ERROR_BOTH_TARGETS);
  }
}
