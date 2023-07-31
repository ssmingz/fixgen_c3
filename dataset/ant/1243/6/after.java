class PlaceHold {
  @Test
  public void testFrom() {
    buildRule.executeTarget("testFrom");
    assertContains(ERROR_FROM_FORBIDDEN, buildRule.getLog());
  }
}
