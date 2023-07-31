class PlaceHold {
  @Test
  public void testText() {
    buildRule.executeTarget("text");
    assertEquals("Inner Text", buildRule.getLog());
  }
}
