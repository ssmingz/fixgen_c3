class PlaceHold {
  @Test
  public void testTailLines() throws IOException {
    buildRule.executeTarget("testTailLines");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.tailLines.test");
    File result =
        new File(buildRule.getProject().getProperty("output") + "/head-tail.tailLines.test");
    assertEquals(
        "testTailLines: Result not like expected",
        FileUtilities.getFileContents(expected),
        FileUtilities.getFileContents(result));
  }
}
