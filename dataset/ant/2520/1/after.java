class PlaceHold {
  public void assertFileIsNotPresent(String f) {
    assertTrue("Didn't expect file " + f, !buildRule.getProject().resolveFile(f).exists());
  }
}
