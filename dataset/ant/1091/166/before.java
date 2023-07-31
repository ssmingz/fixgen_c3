class PlaceHold {
  public void testEchoToGoodFileNoFail() throws Exception {
    executeTarget("testEchoToGoodFileNoFail");
    assertGoodFile();
  }
}
