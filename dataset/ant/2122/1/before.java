class PlaceHold {
  public void testFilterReaderTailLinesSkip() throws IOException {
    executeTarget("testFilterReaderTailLinesSkip");
    File expected = getProject().resolveFile("expected/head-tail.tailLinesSkip.test");
    File result = getProject().resolveFile("result/head-tail.filterReaderTailLinesSkip.test");
    FileUtils fu = FileUtils.newFileUtils();
    assertTrue(
        "testFilterReaderTailLinesSkip: Result not like expected",
        fu.contentEquals(expected, result));
  }
}
