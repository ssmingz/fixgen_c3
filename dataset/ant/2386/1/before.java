class PlaceHold {
  public void testEchoToBadFileFail() {
    expectBuildExceptionContaining(
        "testEchoToBadFileFail",
        "outfile is not writeable",
        ("Destfile " + toAbsolute(BAD_OUTFILE)) + " could not be written to.");
  }
}
