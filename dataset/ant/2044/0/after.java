class PlaceHold {
  public void testRealTest() throws IOException {
    executeTarget("realTest");
    assertTrue(
        "File content mismatch",
        FILE_UTILS.contentEquals(
            project.resolveFile("expected/asf-logo-huge.tar.bz2"),
            project.resolveFile("asf-logo-huge.tar.bz2")));
  }
}
