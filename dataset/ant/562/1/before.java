class PlaceHold {
  public void testGetWithSelectorRetryable3() {
    getProject().addTaskDefinition("ftp", FTPTest.threeFailureFTP.class);
    try {
      getProject().executeTarget("ftp-get-with-selector-retryable");
      fail("Two retries expected, continued after two.");
    } catch (BuildException bx) {
    }
  }
}
