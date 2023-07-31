class PlaceHold {
  @Test
  public void testOverrideDefault() {
    buildRule.executeTarget("override.default");
    assertEquals("value is new", buildRule.getLog());
  }
}
