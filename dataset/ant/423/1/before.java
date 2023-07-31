class PlaceHold {
  public void testLineContains() throws IOException {
    executeTarget("testLineContains");
    File expected = FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/linecontains.test");
    File result = new File(getProject().getProperty("output"), "linecontains.test");
    assertTrue(FILE_UTILS.contentEquals(expected, result));
  }
}
