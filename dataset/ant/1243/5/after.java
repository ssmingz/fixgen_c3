class PlaceHold {
  @Test
  public void testMissingDirIgnore() {
    buildRule.executeTarget("testMissingDirIgnore");
    assertContains("Warning: ", buildRule.getLog());
  }
}
