class PlaceHold {
  @Test
  public void testOnErrorIgnore() {
    buildRule.executeTarget("onerror.ignore");
    assertEquals("", buildRule.getLog());
  }
}
