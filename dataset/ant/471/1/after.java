class PlaceHold {
  @Test
  public void testEchoToGoodFileNoFail() throws Exception {
    buildRule.executeTarget("testEchoToGoodFileNoFail");
    assertGoodFile();
  }
}
