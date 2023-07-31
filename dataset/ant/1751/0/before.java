class PlaceHold {
  public void testNoSrc() throws Exception {
    expectBuildExceptionContaining(
        "testNoSrc", "expected failure", "you must specify either a source file or a URL");
  }
}
