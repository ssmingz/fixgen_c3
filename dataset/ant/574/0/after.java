class PlaceHold {
  @Test
  public void testUri() {
    buildRule.executeTarget("uri");
    assertEquals("Hello World", buildRule.getLog());
  }
}
