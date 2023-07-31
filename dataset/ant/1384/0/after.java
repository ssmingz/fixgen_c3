class PlaceHold {
  @Test
  public void test24() {
    buildRule.executeTarget("test24");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
