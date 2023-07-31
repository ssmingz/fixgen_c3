class PlaceHold {
  public void testRealTest() throws IOException {
    FileUtils fileUtils = FileUtils.newFileUtils();
    executeTarget("realTest");
    assertTrue(
        "File content mismatch after bunzip2",
        fileUtils.contentEquals(
            project.resolveFile("expected/asf-logo-huge.tar"),
            project.resolveFile("asf-logo-huge.tar")));
  }
}
