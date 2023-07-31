class PlaceHold {
  @Test
  public void testHeadLines() throws IOException {
    buildRule.executeTarget("testHeadLines");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.headLines.test");
    File result =
        new File(buildRule.getProject().getProperty("output") + "/head-tail.headLines.test");
    assertEquals(
        "testHeadLines: Result not like expected",
        FileUtilities.getFileContents(expected),
        FileUtilities.getFileContents(result));
  }
}
