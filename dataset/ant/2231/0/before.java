class PlaceHold {
  public void testPropertyNoValue() throws Exception {
    expectBuildExceptionContaining("testPropertyNoValue", ERROR_NO_VALUE, ERROR_NO_VALUE);
  }
}
