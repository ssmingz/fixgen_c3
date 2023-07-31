class PlaceHold {
  @Test
  public void test9() {
    buildRule.executeTarget("test9");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
