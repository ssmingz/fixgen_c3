class PlaceHold {
  @Test
  public void testIstrueNot() {
    buildRule.executeTarget("istrue-not");
    assertNull(buildRule.getProject().getProperty("istrue-not"));
  }
}
