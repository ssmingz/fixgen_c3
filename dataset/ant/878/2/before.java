class PlaceHold {
  public void testBadURL() throws Exception {
    expectBuildExceptionContaining("testBadURL", "error in URL", ERROR_BAD_URL);
  }
}
