class PlaceHold {
  private void assertLogoUncorrupted() throws IOException {
    assertTrue(
        FILE_UTILS.contentEquals(
            project.resolveFile("../asf-logo.gif"), project.resolveFile("asf-logo.gif")));
  }
}
