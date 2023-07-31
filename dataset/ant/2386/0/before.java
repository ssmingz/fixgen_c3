class PlaceHold {
  public void testEchoToBadFile() {
    expectBuildExceptionContaining(
        "testEchoToBadFile",
        "outfile is not writeable",
        ("Destfile " + toAbsolute(BAD_OUTFILE)) + " could not be written to.");
  }
}
