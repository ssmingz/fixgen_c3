class PlaceHold {
  @Test
  public void testFilterReaderHeadLinesSkip() throws IOException {
    buildRule.executeTarget("testFilterReaderHeadLinesSkip");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.headLinesSkip.test");
    File result =
        new File(
            buildRule.getProject().getProperty("output")
                + "/head-tail.filterReaderHeadLinesSkip.test");
    assertEquals(
        "testFilterReaderHeadLinesSkip: Result not like expected",
        FileUtilities.getFileContents(expected),
        FileUtilities.getFileContents(result));
  }
}
