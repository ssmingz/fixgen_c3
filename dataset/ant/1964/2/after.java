class PlaceHold {
  @Test
  public void test18() {
    buildRule.executeTarget("test18");
    assertNull(buildRule.getProject().getProperty("test"));
  }
}
