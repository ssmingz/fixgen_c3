class PlaceHold {
  @Test
  public void testNoResourceOnErrorNotFail() {
    buildRule.executeTarget("noresourcenotfail");
    assertContains("Could not load definitions from resource ", buildRule.getLog());
  }
}
