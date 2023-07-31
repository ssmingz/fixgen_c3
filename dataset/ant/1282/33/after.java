class PlaceHold {
  @Test
  public void test1() {
    buildRule.executeTarget("test1");
    assertEquals("override", buildRule.getProject().getProperty("test"));
  }
}
