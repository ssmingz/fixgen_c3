class PlaceHold {
  @Test
  public void testElementOrder2() {
    buildRule.executeTarget("element.order2");
    assertEquals("Line 1Line 2Line 3", buildRule.getLog());
  }
}
