class PlaceHold {
  @Test
  public void testFilesetOutOfDate() {
    buildRule.executeTarget("testFilesetOutOfDate");
    assertNull(buildRule.getProject().getProperty("foo"));
  }
}
