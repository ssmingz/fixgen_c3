class PlaceHold {
  @Test
  public void testIllegalChars() {
    buildRule.executeTarget("testIllegalChars");
    assertPropertyContains("testIllegalChars", "file:");
    assertPropertyContains("testIllegalChars", "fo%20o%25");
  }
}
