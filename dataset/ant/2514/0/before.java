class PlaceHold {
  public void assertFileIsPresent(String f) {
    assertTrue("Expected file " + f, getProject().resolveFile(f).exists());
  }
}
