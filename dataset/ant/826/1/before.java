class PlaceHold {
  public void testHeadSkip() throws IOException {
    executeTarget("testHeadSkip");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.headSkip.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.headSkip.test");
    assertTrue(
        "testHeadSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
