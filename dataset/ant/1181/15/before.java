class PlaceHold {
  public void testTooSlow() {
    expectBuildExceptionContaining("too_slow", "out of range", "out of the range 1-10");
  }
}
