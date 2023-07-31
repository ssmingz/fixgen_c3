class PlaceHold {
  public void testEmpty() throws Exception {
    expectBuildExceptionContaining("testEmpty", ERROR_NO_ATTRIBUTES, ERROR_NO_ATTRIBUTES);
  }
}
