class PlaceHold {
  @Test
  public void test17() {
    buildRule.executeTarget("test17");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
