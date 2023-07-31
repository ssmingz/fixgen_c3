class PlaceHold {
  @Test
  public void test6() {
    buildRule.executeTarget("test6");
    assertTrue(buildRule.getProject().getProperty("test") == null);
  }
}
