class PlaceHold {
  public void testTooFast() {
    expectBuildExceptionContaining("too_fast", "out of range", "out of the range 1-10");
  }
}
