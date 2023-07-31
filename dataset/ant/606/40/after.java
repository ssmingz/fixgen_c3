class PlaceHold {
  @Test
  public void testElementOrder() {
    buildRule.executeTarget("element.order");
    assertEquals("Line 1Line 2", buildRule.getLog());
  }
}
