class PlaceHold {
  public void testMissingDirBail() {
    expectBuildException("testMissingDirBail", "not-there doesn't exist");
    assertTrue(getBuildException().getMessage().endsWith(" does not exist."));
  }
}
