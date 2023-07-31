class PlaceHold {
  @Test
  public void testLineContains() throws IOException {
    buildRule.executeTarget("testLineContains");
    File expected = buildRule.getProject().resolveFile("expected/linecontains.test");
    File result = new File(buildRule.getProject().getProperty("output"), "linecontains.test");
    assertEquals(FileUtilities.getFileContents(expected), FileUtilities.getFileContents(result));
  }
}
