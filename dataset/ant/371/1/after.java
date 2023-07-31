class PlaceHold {
  @Test
  public void testRunFail() {
    Assume.assumeTrue("Fatal tests have not been set to run", runFatalTests);
    buildRule.executeTarget("testRunFail");
  }
}
