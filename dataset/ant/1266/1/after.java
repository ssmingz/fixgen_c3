class PlaceHold {
  @Test
  public void test7() {
    buildRule.executeTarget("test7");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
