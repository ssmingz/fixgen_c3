class PlaceHold {
  public void testRunAdapterError() {
    expectBuildExceptionContaining("runadaptererror", "xx", "No public run() method in");
  }
}
