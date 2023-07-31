class PlaceHold {
  @Test
  public void test20() {
    buildRule.executeTarget("test20");
    assertNull(buildRule.getProject().getProperty("test"));
  }
}
