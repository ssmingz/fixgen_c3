class PlaceHold {
  @Test
  public void testRCUpToDate() {
    buildRule.executeTarget("testRCUpToDate");
    assertEquals("true", buildRule.getProject().getProperty("foo"));
  }
}
