class PlaceHold {
  @Test
  public void testEscapeUnicode() throws IOException {
    buildRule.executeTarget("testEscapeUnicode");
    File expected = buildRule.getProject().resolveFile("expected/escapeunicode.test");
    File result = new File(buildRule.getProject().getProperty("output"), "escapeunicode.test");
    assertEquals(FileUtilities.getFileContents(expected), FileUtilities.getFileContents(result));
  }
}
