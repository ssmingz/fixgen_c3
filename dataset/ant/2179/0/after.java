class PlaceHold {
  @Test
  public void test8() {
    buildRule.executeTarget("test8");
    assertTrue(buildRule.getProject().getProperty("test") == null);
  }
}
