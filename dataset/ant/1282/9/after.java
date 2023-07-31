class PlaceHold {
  @Test
  public void test13b() {
    buildRule.executeTarget("test13b");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
