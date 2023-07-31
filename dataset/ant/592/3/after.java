class PlaceHold {
  @Test
  public void testPreset() {
    buildRule.executeTarget("testPreset");
    assertEquals("true", buildRule.getProject().getProperty("testPreset"));
  }
}
