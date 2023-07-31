class PlaceHold {
  @Test
  public void testHeadLinesSkip() throws IOException {
    buildRule.executeTarget("testHeadLinesSkip");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.headLinesSkip.test");
    File result =
        new File(buildRule.getProject().getProperty("output") + "/head-tail.headLinesSkip.test");
    assertEquals(
        "testHeadLinesSkip: Result not like expected",
        FileUtilities.getFileContents(expected),
        FileUtilities.getFileContents(result));
  }
}
