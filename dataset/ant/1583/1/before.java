class PlaceHold {
  public void testReplaceTokens() throws IOException {
    executeTarget("testReplaceTokens");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/replacetokens.test");
    File result = new File(getProject().getProperty("output"), "replacetokens.test");
    assertTrue(FILE_UTILS.contentEquals(expected, result));
  }
}
