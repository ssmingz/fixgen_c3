class PlaceHold {
  @Test
  public void testNsAttributes() {
    buildRule.executeTarget("ns.attributes");
    assertEquals("hello world", buildRule.getLog());
  }
}
