class PlaceHold {
  @Test
  public void test16() {
    buildRule.executeTarget("test16");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
