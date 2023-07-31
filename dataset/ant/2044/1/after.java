class PlaceHold {
  public void testRealTest() throws IOException {
    executeTarget("realTest");
    assertTrue(
        "File content mismatch after bunzip2",
        FILE_UTILS.contentEquals(
            project.resolveFile("expected/asf-logo-huge.tar"),
            project.resolveFile("asf-logo-huge.tar")));
  }
}
