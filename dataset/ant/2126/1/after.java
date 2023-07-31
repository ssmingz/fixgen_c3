class PlaceHold {
  @Test
  public void testGetWithSelectorRetryable3() {
    buildRule.getProject().addTaskDefinition("ftp", FTPTest.threeFailureFTP.class);
    try {
      buildRule.getProject().executeTarget("ftp-get-with-selector-retryable");
      fail("Two retries expected, continued after two.");
    } catch (BuildException bx) {
    }
  }
}
