class PlaceHold {
  private void assertFileExists(String message, String filename) {
    assertTrue(message, getProject().resolveFile(filename).exists());
  }
}
