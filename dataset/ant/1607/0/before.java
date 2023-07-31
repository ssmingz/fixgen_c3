class PlaceHold {
  public void testTailLinesSkip() throws IOException {
    executeTarget("testTailLinesSkip");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.tailLinesSkip.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.tailLinesSkip.test");
    assertTrue(
        "testTailLinesSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
