class PlaceHold {
  @Test
  public void testRCOutOfDate() {
    buildRule.executeTarget("testRCOutOfDate");
    assertNull(buildRule.getProject().getProperty("foo"));
  }
}
