class PlaceHold {
  @Test
  public void test4() {
    buildRule.executeTarget("test4");
    assertEquals("original", buildRule.getProject().getProperty("test"));
  }
}
