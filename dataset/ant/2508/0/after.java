class PlaceHold {
  @Test
  public void testFilterReaderTailLinesSkip() throws IOException {
    buildRule.executeTarget("testFilterReaderTailLinesSkip");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.tailLinesSkip.test");
    File result =
        new File(
            buildRule.getProject().getProperty("output")
                + "/head-tail.filterReaderTailLinesSkip.test");
    assertEquals(
        "testFilterReaderTailLinesSkip: Result not like expected",
        FileUtilities.getFileContents(expected),
        FileUtilities.getFileContents(result));
  }
}
