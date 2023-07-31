class PlaceHold {
  public void testExceptingFoe() {
    expectBuildExceptionContaining(
        "testExceptingFoe", "passes exception through", "Exception raised inside called program");
  }
}
