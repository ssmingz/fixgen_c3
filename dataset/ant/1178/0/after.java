class PlaceHold {
  @Test
  public void testEchoToBadFileNoFail() {
    buildRule.executeTarget("testEchoToBadFileNoFail");
    assertContains("destfile is a directory!", buildRule.getLog());
  }
}
