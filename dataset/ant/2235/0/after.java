class PlaceHold {
  @Test
  public void testNoTimeout() {
    buildRule.executeTarget("notimeout");
    assertNull(buildRule.getProject().getProperty("timeout"));
  }
}
