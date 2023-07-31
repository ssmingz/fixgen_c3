class PlaceHold {
  @Test
  public void testNoResourceOnErrorFail() {
    buildRule.executeTarget("noresourcefail");
    assertContains("Could not load definitions from resource ", buildRule.getLog());
  }
}
