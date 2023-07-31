class PlaceHold {
  public void testGetWithSelectorRetryableRandom() {
    getProject().addTaskDefinition("ftp", FTPTest.randomFailureFTP.class);
    try {
      getProject().setProperty("ftp.retries", "forever");
      getProject().executeTarget("ftp-get-with-selector-retryable");
    } catch (BuildException bx) {
      fail("Retry forever specified, but failed.");
    }
  }
}
