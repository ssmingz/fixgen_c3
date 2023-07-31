class PlaceHold {
  @Test
  public void test22() {
    buildRule.executeTarget("test22");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
