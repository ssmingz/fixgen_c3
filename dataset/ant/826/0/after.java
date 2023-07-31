class PlaceHold {
  @Test
  public void testHeadTail() throws IOException {
    buildRule.executeTarget("testHeadTail");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.headtail.test");
    File result =
        new File(buildRule.getProject().getProperty("output") + "/head-tail.headtail.test");
    assertEquals(
        "testHeadTail: Result not like expected",
        FileUtilities.getFileContents(expected),
        FileUtilities.getFileContents(result));
  }
}
