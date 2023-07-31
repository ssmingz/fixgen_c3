class PlaceHold {
  private void assertLogoUncorrupted() throws IOException {
    assertTrue(
        FILE_UTILS.contentEquals(
            project.resolveFile("../asf-logo.gif"),
            new File(getProject().getProperty("output"), "asf-logo.gif")));
  }
}
