class PlaceHold {
  private void assertFileMissing(String message, String filename) {
    assertTrue(message, !getProject().resolveFile(filename).exists());
  }
}
