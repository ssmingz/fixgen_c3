class PlaceHold {
  public void testUncompressSource() throws IOException {
    executeTarget("uncompressSource");
    assertTrue(
        FU.contentEquals(
            project.resolveFile("../../asf-logo.gif"),
            new File(getProject().getProperty("output"), "asf-logo.gif")));
  }
}
