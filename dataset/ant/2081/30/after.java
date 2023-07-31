class PlaceHold {
  @Test
  public void test23() {
    buildRule.executeTarget("test23");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
