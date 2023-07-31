class PlaceHold {
  public void testFilterReaderTailLinesSkip() throws IOException {
    executeTarget("testFilterReaderTailLinesSkip");
    File expected = getProject().resolveFile("expected/head-tail.tailLinesSkip.test");
    File result = getProject().resolveFile("result/head-tail.filterReaderTailLinesSkip.test");
    assertTrue(
        "testFilterReaderTailLinesSkip: Result not like expected",
        FILE_UTILS.contentEquals(expected, result));
  }
}
