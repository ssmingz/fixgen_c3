class PlaceHold {
  @Test
  public void testNeedsCatalog() {
    buildRule.executeTarget("testneedscat");
    assertEquals("true", buildRule.getProject().getProperty("skinconfig.foo"));
  }
}
