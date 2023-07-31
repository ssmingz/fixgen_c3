class PlaceHold {
  public void testFilterReaderHeadLinesSkip() throws IOException {
    executeTarget("testFilterReaderHeadLinesSkip");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.headLinesSkip.test");
    File result =
        new File(getProject().getProperty("output") + "/head-tail.filterReaderHeadLinesSkip.test");
    assertTrue(
        "testFilterReaderHeadLinesSkip: Result not like expected",
        FILE_UTILS.contentEquals(expected, result));
  }
}
