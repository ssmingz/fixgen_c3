class PlaceHold {
  @Test
  public void testDoubleDefault() {
    buildRule.executeTarget("doubledefault");
    assertEquals("attribute is falseattribute is true", buildRule.getLog());
  }
}
