class PlaceHold {
  public void testEchoToBadFile() {
    expectBuildExceptionContaining(
        "testEchoToBadFile", "destfile is a directory", "destfile is a directory!");
  }
}
