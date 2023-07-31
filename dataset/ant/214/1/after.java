class PlaceHold {
  @Test
  public void testIgnoreElementCase() {
    buildRule.executeTarget("ignore-element-case");
    assertEquals("nested elementnested element", buildRule.getLog());
  }
}
