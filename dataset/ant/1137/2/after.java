class PlaceHold {
  @Test
  public void testGetWithSelectorRetryableRandom() {
    buildRule.getProject().addTaskDefinition("ftp", FTPTest.randomFailureFTP.class);
    try {
      buildRule.getProject().setProperty("ftp.retries", "forever");
      buildRule.getProject().executeTarget("ftp-get-with-selector-retryable");
    } catch (BuildException bx) {
      fail("Retry forever specified, but failed.");
    }
  }
}
