class PlaceHold {
  @Test
  public void testBackTrace() {
    try {
      buildRule.executeTarget("backtraceon");
      fail("BuildException expected: Checking if a back trace is created");
    } catch (BuildException ex) {
      assertContains("following error occurred", ex.getMessage());
    }
  }
}
