class PlaceHold {
  @Test
  public void testUpdateNotNecessary() {
    buildRule.executeTarget("testUpdateNotNecessary");
    assertEquals(-1, buildRule.getLog().indexOf("Updating"));
  }
}
