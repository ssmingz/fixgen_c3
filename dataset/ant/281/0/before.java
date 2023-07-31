class PlaceHold {
  public void testException() {
    expectBuildExceptionContaining(
        "exception", "Should have thrown an exception in the script", "TypeError");
  }
}
