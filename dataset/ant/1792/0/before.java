class PlaceHold {
  public void testBadTimeout() throws Exception {
    expectBuildExceptionContaining("testBadTimeout", "error on -ve timeout", ERROR_BAD_TIMEOUT);
  }
}
