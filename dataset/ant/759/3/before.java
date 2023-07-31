class PlaceHold {
  public void testBadName() throws Exception {
    expectBuildExceptionContaining("testBadName", "compiler not known", ERROR_UNKNOWN_COMPILER);
  }
}
