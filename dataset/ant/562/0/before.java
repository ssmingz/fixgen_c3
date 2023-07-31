class PlaceHold {
  public void testGetWithSelectorRetryable2() {
    getProject().addTaskDefinition("ftp", FTPTest.twoFailureFTP.class);
    try {
      getProject().executeTarget("ftp-get-with-selector-retryable");
    } catch (BuildException bx) {
      fail("Two retries expected, failed after two.");
    }
  }
}
