class PlaceHold {
  @Test
  public void testUri() {
    buildRule.executeTarget("uri");
    assertEquals("Hello world", buildRule.getLog());
  }
}
