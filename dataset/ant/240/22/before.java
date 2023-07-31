class PlaceHold {
  public void testEmpty() {
    expectBuildExceptionContaining("testEmpty", "must fail", "No nested XML specified");
  }
}
