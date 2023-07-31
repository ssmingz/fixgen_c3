class PlaceHold {
  public void testEscapeUnicode() throws IOException {
    executeTarget("testEscapeUnicode");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/escapeunicode.test");
    File result = new File(getProject().getProperty("output"), "escapeunicode.test");
    assertTrue(FILE_UTILS.contentEquals(expected, result));
  }
}
