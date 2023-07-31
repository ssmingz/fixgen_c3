class PlaceHold {
  @Test
  public void test11() {
    buildRule.executeTarget("test11");
    assertNull(buildRule.getProject().getProperty("test"));
  }
}
