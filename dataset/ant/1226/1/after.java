class PlaceHold {
  @Test
  public void test15() {
    buildRule.executeTarget("test15");
    assertNull(buildRule.getProject().getProperty("test"));
  }
}
