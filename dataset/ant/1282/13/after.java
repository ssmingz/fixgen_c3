class PlaceHold {
  @Test
  public void test4() {
    buildRule.executeTarget("test4");
    assertTrue(buildRule.getProject().getProperty("test") == null);
  }
}
