class PlaceHold {
  private void assertFileExists(String message, String filename) {
    assertTrue(message, buildRule.getProject().resolveFile(filename).exists());
  }
}
