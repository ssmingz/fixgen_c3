class PlaceHold {
  @Test
  public void testHead() throws IOException {
    buildRule.executeTarget("testHead");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.head.test");
    File result = new File(buildRule.getProject().getProperty("output") + "/head-tail.head.test");
    assertEquals(
        "testHead: Result not like expected",
        FileUtilities.getFileContents(expected),
        FileUtilities.getFileContents(result));
  }
}
