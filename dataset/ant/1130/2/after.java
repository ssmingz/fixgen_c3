class PlaceHold {
  @Test
  public void testSimple() {
    buildRule.executeTarget("simple");
    assertEquals("Hello World", buildRule.getLog());
  }
}
