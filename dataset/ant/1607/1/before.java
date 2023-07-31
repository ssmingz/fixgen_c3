class PlaceHold {
  public void testHeadLinesSkip() throws IOException {
    executeTarget("testHeadLinesSkip");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.headLinesSkip.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.headLinesSkip.test");
    assertTrue(
        "testHeadLinesSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
