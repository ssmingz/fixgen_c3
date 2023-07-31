class PlaceHold {
  @Test
  public void testReplaceTokens() throws IOException {
    buildRule.executeTarget("testReplaceTokens");
    File expected = buildRule.getProject().resolveFile("expected/replacetokens.test");
    File result = new File(buildRule.getProject().getProperty("output"), "replacetokens.test");
    assertEquals(FileUtilities.getFileContents(expected), FileUtilities.getFileContents(result));
  }
}
