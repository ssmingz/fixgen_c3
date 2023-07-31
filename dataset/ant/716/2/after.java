class PlaceHold {
  @Test
  public void testTail() throws IOException {
    buildRule.executeTarget("testTail");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.tail.test");
    File result = new File(buildRule.getProject().getProperty("output") + "/head-tail.tail.test");
    assertEquals(
        "testTail: Result not like expected",
        FileUtilities.getFileContents(expected),
        FileUtilities.getFileContents(result));
  }
}
