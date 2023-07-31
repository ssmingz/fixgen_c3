class PlaceHold {
  @Test
  public void testDoubleNotPresent() {
    buildRule.executeTarget("double-notpresent");
    assertContains("hi", buildRule.getLog());
  }
}
