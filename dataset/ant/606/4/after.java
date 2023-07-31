class PlaceHold {
  @Test
  public void testDefaultTest() {
    buildRule.executeTarget("defaulttest");
    assertEquals("attribute is false", buildRule.getLog());
  }
}
