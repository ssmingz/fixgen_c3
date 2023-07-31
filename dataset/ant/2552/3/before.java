class PlaceHold {
  public void testStripJavaComments() throws IOException {
    executeTarget("testStripJavaComments");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/stripjavacomments.test");
    File result = new File(getProject().getProperty("output"), "stripjavacomments.test");
    assertTrue(FILE_UTILS.contentEquals(expected, result));
  }
}
