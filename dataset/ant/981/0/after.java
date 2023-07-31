class PlaceHold {
  @Test
  public void testExcepting() {
    buildRule.executeTarget("testExcepting");
    assertContains("Exception raised inside called program", buildRule.getLog());
  }
}
