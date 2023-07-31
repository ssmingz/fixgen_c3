class PlaceHold {
  @Test
  public void testUpdateIsNecessary() {
    buildRule.executeTarget("testUpdateIsNecessary");
    assertContains("Updating", buildRule.getLog());
  }
}
