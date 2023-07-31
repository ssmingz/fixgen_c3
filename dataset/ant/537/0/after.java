class PlaceHold {
  public void testTailLinesSkip() throws IOException {
    executeTarget("testTailLinesSkip");
    File expected = getProject().resolveFile("expected/head-tail.tailLinesSkip.test");
    File result = getProject().resolveFile("result/head-tail.tailLinesSkip.test");
    assertTrue(
        "testTailLinesSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
