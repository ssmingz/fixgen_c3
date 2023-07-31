class PlaceHold {
  public void testRealTest() throws IOException {
    FileUtils fileUtils = FileUtils.newFileUtils();
    executeTarget("realTest");
    assertTrue(
        "File content mismatch",
        fileUtils.contentEquals(
            project.resolveFile("expected/asf-logo-huge.tar.bz2"),
            project.resolveFile("asf-logo-huge.tar.bz2")));
  }
}
