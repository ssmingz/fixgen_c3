class PlaceHold {
  public void testIllegalChars() {
    executeTarget("testIllegalChars");
    assertPropertyContains("testIllegalChars", "file:");
    assertPropertyContains("testIllegalChars", "fo%20o%25");
  }
}
