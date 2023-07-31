class PlaceHold {
  public void testHeadLinesSkip() throws IOException {
    executeTarget("testHeadLinesSkip");
    File expected = getProject().resolveFile("expected/head-tail.headLinesSkip.test");
    File result = getProject().resolveFile("result/head-tail.headLinesSkip.test");
    FileUtils fu = FileUtils.newFileUtils();
    assertTrue("testHeadLinesSkip: Result not like expected", fu.contentEquals(expected, result));
  }
}
