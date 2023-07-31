class PlaceHold {
  public void testBackTrace() {
    expectBuildExceptionContaining(
        "backtraceon", "Checking if a back trace is created", "following error occurred");
  }
}
