class PlaceHold {
  @Test
  public void testEchoToGoodFileFail() throws Exception {
    buildRule.executeTarget("testEchoToGoodFileFail");
    assertGoodFile();
  }
}
