class PlaceHold {
  public void testEchoToGoodFileFail() throws Exception {
    executeTarget("testEchoToGoodFileFail");
    assertGoodFile();
  }
}
