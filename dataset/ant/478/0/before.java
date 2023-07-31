class PlaceHold {
  public void testBadPattern() {
    expectBuildExceptionContaining("testBadPattern", "No parsing exception thrown", "Unparseable");
  }
}
