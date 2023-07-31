class PlaceHold {
  @Test
  public void testEchoToGoodFile() throws Exception {
    buildRule.executeTarget("testEchoToGoodFile");
    assertGoodFile();
  }
}
