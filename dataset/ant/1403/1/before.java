class PlaceHold {
  public void testMissingFileBail() {
    expectBuildException("testMissingFileBail", "not-there doesn't exist");
    assertTrue(getBuildException().getMessage().startsWith("Warning: Could not find file "));
  }
}
