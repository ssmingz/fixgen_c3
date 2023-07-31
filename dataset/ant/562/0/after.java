class PlaceHold {
  @Test
  public void testGetWithSelectorRetryable2() {
    buildRule.getProject().addTaskDefinition("ftp", FTPTest.twoFailureFTP.class);
    try {
      buildRule.getProject().executeTarget("ftp-get-with-selector-retryable");
    } catch (BuildException bx) {
      fail("Two retries expected, failed after two.");
    }
  }
}
