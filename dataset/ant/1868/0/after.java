class PlaceHold {
  private void assertFileMissing(String message, String filename) {
    assertTrue(message, !buildRule.getProject().resolveFile(filename).exists());
  }
}
