class PlaceHold {
  @Test
  public void testEchoToLog() {
    buildRule.executeTarget("testEchoToLog");
    AntAssert.assertContains("Processing File", buildRule.getLog());
  }
}
