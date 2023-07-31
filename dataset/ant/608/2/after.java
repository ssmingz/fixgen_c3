class PlaceHold {
  @Test
  public void test5() {
    buildRule.executeTarget("test5");
    assertEquals("original", buildRule.getProject().getProperty("test"));
  }
}
