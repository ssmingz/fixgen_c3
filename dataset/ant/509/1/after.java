class PlaceHold {
  @Test
  public void testCanLoad() {
    buildRule.executeTarget("useBeanshell");
    assertEquals("I'm here", buildRule.getLog());
  }
}
