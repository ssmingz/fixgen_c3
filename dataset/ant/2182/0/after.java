class PlaceHold {
  @Test
  public void test21() {
    buildRule.executeTarget("test21");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
