class PlaceHold {
  @Test
  public void test10() {
    buildRule.executeTarget("test10");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
