class PlaceHold {
  public void testFilterReaderHeadLinesSkip() throws IOException {
    executeTarget("testFilterReaderHeadLinesSkip");
    File expected = getProject().resolveFile("expected/head-tail.headLinesSkip.test");
    File result = getProject().resolveFile("result/head-tail.filterReaderHeadLinesSkip.test");
    assertTrue(
        "testFilterReaderHeadLinesSkip: Result not like expected",
        FILE_UTILS.contentEquals(expected, result));
  }
}
