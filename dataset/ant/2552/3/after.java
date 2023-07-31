class PlaceHold {
  @Test
  public void testStripJavaComments() throws IOException {
    buildRule.executeTarget("testStripJavaComments");
    File expected = buildRule.getProject().resolveFile("expected/stripjavacomments.test");
    File result = new File(buildRule.getProject().getProperty("output"), "stripjavacomments.test");
    assertEquals(FileUtilities.getFileContents(expected), FileUtilities.getFileContents(result));
  }
}
