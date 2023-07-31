class PlaceHold {
  public void testEmpty() {
    expectBuildExceptionContaining("testEmpty", "missing property", "property");
  }
}
