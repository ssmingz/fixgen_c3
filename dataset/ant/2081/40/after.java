class PlaceHold {
  @Test
  public void test13() {
    buildRule.executeTarget("test13");
    assertNull(buildRule.getProject().getProperty("test"));
  }
}
