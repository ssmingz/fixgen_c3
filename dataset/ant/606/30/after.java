class PlaceHold {
  @Test
  public void testMultiSameProperty() {
    buildRule.executeTarget("multi-same-property");
    assertEquals("prop is two", buildRule.getLog());
  }
}
