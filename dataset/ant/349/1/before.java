class PlaceHold {
  public void testUncompressSource() throws IOException {
    executeTarget("uncompressSource");
    assertTrue(
        FU.contentEquals(
            project.resolveFile("../../asf-logo.gif"),
            project.resolveFile("testout/asf-logo.gif")));
  }
}
