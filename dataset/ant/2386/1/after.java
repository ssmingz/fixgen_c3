class PlaceHold {
  public void testEchoToBadFileFail() {
    expectBuildExceptionContaining(
        "testEchoToBadFileFail", "destfile is a directory", "destfile is a directory!");
  }
}
