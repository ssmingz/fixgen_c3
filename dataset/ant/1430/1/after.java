class PlaceHold {
  @Test
  public void testAttributeDescription() {
    buildRule.executeTarget("attribute.description");
    assertEquals("description is hello world", buildRule.getLog());
  }
}
