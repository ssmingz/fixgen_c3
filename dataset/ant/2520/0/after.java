class PlaceHold {
  public void assertFileIsPresent(String f) {
    assertTrue("Expected file " + f, buildRule.getProject().resolveFile(f).exists());
  }
}
