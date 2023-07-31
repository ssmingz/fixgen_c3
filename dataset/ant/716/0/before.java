class PlaceHold {
  public void testFilterReaderTailLinesSkip() throws IOException {
    executeTarget("testFilterReaderTailLinesSkip");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.tailLinesSkip.test");
    File result =
        new File(getProject().getProperty("output") + "/head-tail.filterReaderTailLinesSkip.test");
    assertTrue(
        "testFilterReaderTailLinesSkip: Result not like expected",
        FILE_UTILS.contentEquals(expected, result));
  }
}
