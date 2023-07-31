class PlaceHold {
  @Test
  public void testTimeout() {
    buildRule.executeTarget("timeout");
    assertEquals("true", buildRule.getProject().getProperty("timeout"));
  }
}
