class PlaceHold {
  public void testHeadLines() throws IOException {
    executeTarget("testHeadLines");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.headLines.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.headLines.test");
    assertTrue(
        "testHeadLines: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
