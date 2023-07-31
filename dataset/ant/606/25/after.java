class PlaceHold {
  @Test
  public void testOther() {
    buildRule.executeTarget("other");
    assertEquals("a message", buildRule.getLog());
  }
}
