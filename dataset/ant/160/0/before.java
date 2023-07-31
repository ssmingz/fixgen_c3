class PlaceHold {
  public void testBothSrc() throws Exception {
    expectBuildExceptionContaining(
        "testBothSrc", "expected failure", "both a source file and a URL");
  }
}
