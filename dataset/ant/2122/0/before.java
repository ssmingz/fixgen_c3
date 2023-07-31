class PlaceHold {
  public void testFilterReaderHeadLinesSkip() throws IOException {
    executeTarget("testFilterReaderHeadLinesSkip");
    File expected = getProject().resolveFile("expected/head-tail.headLinesSkip.test");
    File result = getProject().resolveFile("result/head-tail.filterReaderHeadLinesSkip.test");
    FileUtils fu = FileUtils.newFileUtils();
    assertTrue(
        "testFilterReaderHeadLinesSkip: Result not like expected",
        fu.contentEquals(expected, result));
  }
}
