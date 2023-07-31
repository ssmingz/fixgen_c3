class PlaceHold {
  public void assertFileIsNotPresent(String f) {
    assertTrue("Didn't expect file " + f, !getProject().resolveFile(f).exists());
  }
}
